package com.example.jsonex.productshop;

import com.example.jsonex.productshop.model.dto.CategoryProductsDto;
import com.example.jsonex.productshop.model.dto.ProductNameAndPriceDto;
import com.example.jsonex.productshop.model.dto.UserSoldDto;
import com.example.jsonex.productshop.model.dto.UsersWithMoreThenOneSoldProductDto;
import com.example.jsonex.productshop.services.CategoryService;
import com.example.jsonex.productshop.services.ProductService;
import com.example.jsonex.productshop.services.UserService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    public static final String OUTPUT_PATH = "src/main/resources/files/out/";
    public static final String PRODUCT_IN_RANGE_FILE = "products-in-range.json";
    public static final String USER_SOLD_PRODUCTS_FILE = "users-sold-products.json";
    public static final String CATEGORIES_BY_PRODUCTS_FILE = "categories-by-products.json";
    public static final String USERS_WITH_PRODUCTS_FILE = "users-with-products.json";

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader reader;
    private final Gson gson;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        System.out.println("Enter query number:");
        int queryNumber = Integer.parseInt(reader.readLine());

        switch (queryNumber) {
            case 1 -> q1ProductsInRange();
            case 2 -> q2SoldProducts();
            case 3 -> q3CategoriesByCount();
            case 4 -> q4UsersWithProducts();
            default -> System.out.println("Invalid!");
        }
    }

    private void q4UsersWithProducts() throws IOException {
        List<UsersWithMoreThenOneSoldProductDto> allUsersWithMoreThenOneSoldProduct = userService
                .findAllUsersWithMoreThenOneSoldProduct();

        String content = gson.toJson(allUsersWithMoreThenOneSoldProduct);

        writeToFile(OUTPUT_PATH + USERS_WITH_PRODUCTS_FILE, content);

        System.out.println("users-with-products.json: READY!");
    }

    private void q3CategoriesByCount() throws IOException {
        List<CategoryProductsDto> categoryProductsDtos = categoryService
                .findAllCategoriesWithProductCount();

        String content = gson.toJson(categoryProductsDtos);

        writeToFile(OUTPUT_PATH + CATEGORIES_BY_PRODUCTS_FILE, content);

        System.out.println("categories-by-products.json: READY!");
    }

    private void q2SoldProducts() throws IOException {
        List<UserSoldDto> userSoldDtos =
                userService.findAllUserWithMoreThenOneProducts();


        String content = gson.toJson(userSoldDtos);

        writeToFile(OUTPUT_PATH + USER_SOLD_PRODUCTS_FILE, content);

        System.out.println("users-sold-products.json: READY!");
    }

    private void q1ProductsInRange() throws IOException {
        List<ProductNameAndPriceDto> productsDtos = productService
                .findAllProductsInRangeOrderByPrice(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L));

        String content = gson.toJson(productsDtos);

        writeToFile(OUTPUT_PATH + PRODUCT_IN_RANGE_FILE, content);

        System.out.println("products-in-range.json: READY!");
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        userService.seedUsers();
        productService.seedProducts();
    }
}
