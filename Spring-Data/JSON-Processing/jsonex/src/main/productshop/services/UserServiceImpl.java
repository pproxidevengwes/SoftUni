package com.example.jsonex.productshop.services;

import com.example.jsonex.productshop.constants.GlobalConstants;
import com.example.jsonex.productshop.model.dto.UserSeedDto;
import com.example.jsonex.productshop.model.dto.UserSoldDto;
import com.example.jsonex.productshop.model.dto.UsersWithMoreThenOneSoldProductDto;
import com.example.jsonex.productshop.model.entity.User;
import com.example.jsonex.productshop.repositories.UserRepository;
import com.example.jsonex.productshop.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final static String USERS_FILE_NAME = "users.json";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers() throws IOException {

        if (userRepository.count() == 0) {

            String fileContent = Files
                    .readString(Path.of(GlobalConstants.RESOURCE_FILE_PATH + USERS_FILE_NAME));


            Arrays.stream(gson.fromJson(fileContent, UserSeedDto[].class))
                    .filter(validationUtil::isValid)
                    .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                    .forEach(userRepository::save);
        }
    }

    @Override
    public User findRandomUser() {

        long randomId = ThreadLocalRandom
                .current().nextLong(1, userRepository.count() + 1);

        return userRepository.findById(randomId)
                .orElse(null);
    }

    @Override
    public List<UserSoldDto> findAllUserWithMoreThenOneProducts() {
        return userRepository.findAllUsersWithMoreThenOneSoldProductOrderByLastNameThenFirstName()
                .stream()
                .map(user -> modelMapper.map(user, UserSoldDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsersWithMoreThenOneSoldProductDto> findAllUsersWithMoreThenOneSoldProduct() {
        return userRepository.findAllUsersWithMoreThenOneSoledProducts()
                .stream()
                .map(user -> modelMapper.map(user, UsersWithMoreThenOneSoldProductDto.class))
                .collect(Collectors.toList());
    }
}
