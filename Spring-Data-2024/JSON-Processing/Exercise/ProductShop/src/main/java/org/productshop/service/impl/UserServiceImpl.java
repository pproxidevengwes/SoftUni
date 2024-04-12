package org.productshop.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.productshop.data.entities.User;
import org.productshop.data.repositories.UserRepository;
import org.productshop.service.UserService;
import org.productshop.service.dtos.export.*;
import org.productshop.service.dtos.imports.UserSeedDto;
import org.productshop.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final String FILE_PATH = "src/main/resources/json/users.json";
    private final UserRepository userRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        if (this.userRepository.count() == 0) {
            UserSeedDto[] userSeedDtos = this.gson.fromJson(new FileReader(FILE_PATH), UserSeedDto[].class);
            for (UserSeedDto userSeedDto : userSeedDtos) {
                if (!this.validationUtil.isValid(userSeedDto)) {
                    this.validationUtil.getViolations(userSeedDto)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                User category = this.modelMapper.map(userSeedDto, User.class);
                this.userRepository.saveAndFlush(category);

            }
        }
    }

    @Override
    public List<UserSoldProductsDto> getAllUsersSoldItems() {

        return this.userRepository.findAll()
                .stream()
                .filter(u ->
                        u.getSold().stream().anyMatch(p -> p.getBuyer() != null))
                .map(u -> {
                    UserSoldProductsDto dto = this.modelMapper.map(u, UserSoldProductsDto.class);
                    List<ProductSoldDto> soldProductsDto = u.getSold()
                            .stream()
                            .filter(p -> p.getBuyer() != null)
                            .map(p -> this.modelMapper.map(p, ProductSoldDto.class))
                            .collect(Collectors.toList());
                    dto.setSoldProducts(soldProductsDto);
                    return dto;
                })
                .sorted(Comparator.comparing(UserSoldProductsDto::getLastName).thenComparing(UserSoldProductsDto::getFirstName))
                .toList();
    }

    @Override
    public void printAllUsersAndSoldItems() {
        String json = this.gson.toJson(this.getAllUsersSoldItems());
        System.out.println(json);
    }

    @Override
    public UserAndProductsDto getUserAndProductDto() {
        UserAndProductsDto userAndProductDto = new UserAndProductsDto();
        List<UserSoldDto> userSoldDtos = this.userRepository.findAll()
                .stream()
                .filter(u -> !u.getSold().isEmpty())
                .map(u -> {
                    UserSoldDto userSoldDto = this.modelMapper.map(u, UserSoldDto.class);
                    ProductSoldByUserDto productSoldByUserDto = new ProductSoldByUserDto();

                    List<ProductInfoDto> productInfoDtos = u.getSold().stream()
                            .map(p -> this.modelMapper.map(p, ProductInfoDto.class))
                            .collect(Collectors.toList());
                    productSoldByUserDto.setProducts(productInfoDtos);
                    productSoldByUserDto.setCount(productInfoDtos.size());


                    userSoldDto.setSoldProducts(productSoldByUserDto);
                    return userSoldDto;
                }).sorted(Comparator.comparingInt(a -> a.getSoldProducts().getCount())
                ).collect(Collectors.toList());
        userAndProductDto.setUsers(userSoldDtos);
        userAndProductDto.setUsersCount(userSoldDtos.size());
        return userAndProductDto;
}

@Override
public void printGetUserAndProductDto() {
    String json = this.gson.toJson(this.getUserAndProductDto());
    System.out.println(json);
}
}
