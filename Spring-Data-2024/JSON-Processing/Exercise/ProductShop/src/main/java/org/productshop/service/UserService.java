package org.productshop.service;

import org.productshop.service.dtos.export.UserAndProductsDto;
import org.productshop.service.dtos.export.UserSoldProductsDto;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {
    void seedUsers() throws FileNotFoundException;

    List<UserSoldProductsDto> getAllUsersSoldItems();

    void printAllUsersAndSoldItems();

    UserAndProductsDto getUserAndProductDto();

    void printGetUserAndProductDto();
}
