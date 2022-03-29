package com.example.cardealer.services;

import com.example.cardealer.constants.GlobalApplicationConstants;
import com.example.cardealer.model.dto.PartsSeedDto;
import com.example.cardealer.model.entity.Part;
import com.example.cardealer.repositories.PartRepository;
import com.example.cardealer.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartServiceImpl implements PartService {
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final PartRepository partRepository;
    private final SupplierService supplierService;

    public PartServiceImpl(Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, PartRepository partRepository, SupplierService supplierService) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.partRepository = partRepository;
        this.supplierService = supplierService;
    }

    @Override
    public void seedParts() throws IOException {
        if (this.partRepository.count() > 0) {
            return;
        }

        String data = Files.readString(Path.of(GlobalApplicationConstants.RESOURCE_FILE_PATH + "parts.json"));
        PartsSeedDto[] partsSeedDtos = gson.fromJson(data, PartsSeedDto[].class);
        Arrays.stream(partsSeedDtos)
                .filter(validationUtil::isValid)
                .map(partsSeedDto -> {
                    Part part = modelMapper.map(partsSeedDto, Part.class);
                    part.setSupplier(supplierService.getRandomSupplier());

                    return part;
                })
                .forEach(partRepository::save);

    }

    @Override
    public Part getRandomPart() {
        long count = this.partRepository.count();
        long randomId = ThreadLocalRandom.current().nextLong(1, count + 1);

        return partRepository.getById(randomId);
    }
}
