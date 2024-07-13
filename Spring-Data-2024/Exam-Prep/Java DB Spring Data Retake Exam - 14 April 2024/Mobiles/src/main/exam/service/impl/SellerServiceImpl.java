package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerImportDto;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    private final String SELLER_FILE_PATH = "C:\\Users\\User\\Downloads\\Mobiles-Skeleton\\Mobiles\\src\\main\\resources\\files\\json\\sellers.json";
    private final ModelMapper modelMapper;

    private final Gson gson;

    private final ValidationUtil validationUtil;
    private final SellerRepository sellerRepository;

    public SellerServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, SellerRepository sellerRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(SELLER_FILE_PATH)));

    }

    @Override
    public String importSellers() throws IOException {
        StringBuilder sb = new StringBuilder();
        SellerImportDto[] sellerImportDtos = gson.fromJson(readSellersFromFile(), SellerImportDto[].class);
        for (SellerImportDto sellerImportDto : sellerImportDtos) {
            Optional<Seller> optional = sellerRepository.findByLastName(sellerImportDto.getLastName());
            Optional<Seller> optional2 = sellerRepository.findByFirstName(sellerImportDto.getFirstName());
            if (!validationUtil.isValid(sellerImportDto) || optional.isPresent()||sellerImportDto.getFirstName().length()>2||sellerImportDto.getFirstName().length()<30||
                    sellerImportDto.getLastName().length()>2||sellerImportDto.getLastName().length()<30) {
                sb.append("Invalid seller\n");
                continue;
            }
            Seller seller = modelMapper.map(sellerImportDto, Seller.class);
            sellerRepository.saveAndFlush(seller);
            sb.append(String.format("Successfully imported seller %s %s\n", seller.getFirstName(), seller.getLastName()));
        }


        return sb.toString();
    }
}
