package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SaleImportDto;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SaleRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {
    private final String SALE_FILE_PATH = "C:\\Users\\User\\Downloads\\Mobiles-Skeleton\\Mobiles\\src\\main\\resources\\files\\json\\sales.json";
    private final ModelMapper modelMapper;

    private final Gson gson;

    private final ValidationUtil validationUtil;
    private final SaleRepository saleRepository;
    private final SellerRepository sellerRepository;

    public SaleServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, SaleRepository saleRepository, SellerRepository sellerRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.saleRepository = saleRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.saleRepository.count() > 0;
    }

    @Override
    public String readSalesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(SALE_FILE_PATH)));

    }

    @Override
    public String importSales() throws IOException {

        StringBuilder sb = new StringBuilder();

        SaleImportDto[] saleImportDtos = gson.fromJson(readSalesFileContent(), SaleImportDto[].class);
        for (SaleImportDto saleImportDto : saleImportDtos) {
            Optional<Sale> optional = saleRepository.findByNumber(saleImportDto.getNumber());
            if (!validationUtil.isValid(saleImportDto) || optional.isPresent()||saleImportDto.getNumber().length()!=7) {
                sb.append("Invalid sale\n");
                continue;
            }

            Sale sale = modelMapper.map(saleImportDto, Sale.class);
//            sale.setSaleDate(LocalDateTimeUtil.serialize(saleImportDto.getSaleDate()));
            saleRepository.saveAndFlush(sale);
            sb.append(String.format("Successfully imported sale with number %s\n", sale.getNumber()));
        }


        return sb.toString();
    }
}
