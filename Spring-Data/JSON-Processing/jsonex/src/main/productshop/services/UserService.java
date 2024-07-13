package com.example.jsonex.productshop.services;

import com.example.jsonex.productshop.model.dto.UserSoldDto;
import com.example.jsonex.productshop.model.dto.UsersWithMoreThenOneSoldProductDto;
import com.example.jsonex.productshop.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;

    User findRandomUser();

    List<UserSoldDto> findAllUserWithMoreThenOneProducts();

    List<UsersWithMoreThenOneSoldProductDto> findAllUsersWithMoreThenOneSoldProduct();
}
