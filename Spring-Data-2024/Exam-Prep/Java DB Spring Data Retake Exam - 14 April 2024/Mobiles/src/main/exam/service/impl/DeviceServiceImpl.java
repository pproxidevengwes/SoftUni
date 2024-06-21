package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DeviceImportDto;
import softuni.exam.models.dto.DeviceRootDto;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {
    private static final String DEVICE_IMPORT = "C:\\Users\\User\\Downloads\\Mobiles-Skeleton\\Mobiles\\src\\main\\resources\\files\\xml\\devices.xml";
    private final ModelMapper modelMapper;

    private final Gson gson;
    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;
    private final DeviceRepository deviceRepository;
    private final SaleRepository saleRepository;


    public DeviceServiceImpl(ModelMapper modelMapper, Gson gson, XmlParser xmlParser, ValidationUtil validationUtil, DeviceRepository deviceRepository, SaleRepository saleRepository) throws JAXBException {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.deviceRepository = deviceRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public boolean areImported() {
        return this.deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(DEVICE_IMPORT)));

    }

    @Override
    public String importDevices() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        JAXBContext jaxbContext = JAXBContext.newInstance(DeviceRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        DeviceRootDto deviceRootDto = (DeviceRootDto) unmarshaller.unmarshal(new File(DEVICE_IMPORT));
        for (DeviceImportDto deviceImportDto : deviceRootDto.getDeviceImportDtotoList()) {

            Optional<Device> optionalDevice = this.deviceRepository.findByModelAndBrand(deviceImportDto.getModel(), deviceImportDto.getBrand());

            Optional<Sale> optionalSale = this.saleRepository.findById(deviceImportDto.getSale());
            if (!this.validationUtil.isValid(deviceImportDto) || optionalDevice.isPresent() || optionalSale.isEmpty()) {
                sb.append("Invalid device\n");
                continue;
            }

            Device device = this.modelMapper.map(deviceImportDto, Device.class);
            device.setSale(optionalSale.get());
            this.deviceRepository.saveAndFlush(device);

            sb.append(String.format("Successfully imported device of type %s with brand %s\n",
                    device.getDeviceType(), device.getBrand()));
        }
        return sb.toString();
    }

    @Override
    public String exportDevices() {
        return this.deviceRepository.findDeviceByDeviceTypeAndPriceLessThanAndStorageGreaterThan()
                .stream()
                .sorted((d1, d2) -> d1.getBrand().compareToIgnoreCase(d2.getBrand()))
                .map(s -> String.format("Device brand: %s\n" +
                                "   *Model: %s\n" +
                                "   **Storage: %d\n" +
                                "   ***Price: %.2f\n",
                        s.getBrand(), s.getModel(), s.getStorage(), s.getPrice()))
                .collect(Collectors.joining());
    }
}
