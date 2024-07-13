package com.example.jsonex.productshop.services;

import com.example.jsonex.productshop.constants.GlobalConstants;
import com.example.jsonex.productshop.model.dto.CategoryProductsDto;
import com.example.jsonex.productshop.model.dto.CategorySeedDto;
import com.example.jsonex.productshop.model.entity.Category;
import com.example.jsonex.productshop.model.entity.Product;
import com.example.jsonex.productshop.repositories.CategoryRepository;
import com.example.jsonex.productshop.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final static String CATEGORIES_FILE_NAME = "categories.json";

    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(Gson gson, ValidationUtil validationUtil, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {

        if (categoryRepository.count() > 0) {
            return;
        }

        String fileContent = Files
                .readString(Path.of(GlobalConstants.RESOURCE_FILE_PATH + CATEGORIES_FILE_NAME));

        CategorySeedDto[] categorySeedDtos = gson
                .fromJson(fileContent, CategorySeedDto[].class);

        Arrays.stream(categorySeedDtos)
                .filter(validationUtil::isValid)
                .map(CategorySeedDto -> modelMapper.map(CategorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public Set<Category> findRandomCategories() {

        Set<Category> categorySet = new HashSet<>();

        int catCount = ThreadLocalRandom.current().nextInt(1, 3);
        long totalCategoriesCount = categoryRepository.count();

        for (int i = 0; i < catCount; i++) {
            long randomId = ThreadLocalRandom
                    .current()
                    .nextLong(1, totalCategoriesCount + 1);

            categorySet
                    .add(categoryRepository.findById(randomId).orElse(null));
        }

        return categorySet;
    }

    @Override
    public List<CategoryProductsDto> findAllCategoriesWithProductCount() {
        return categoryRepository.findAllCategoriesWithTheirCountOfProducts()
                .stream()
                .map(category -> {
                    CategoryProductsDto categoryProductsDto = modelMapper
                            .map(category, CategoryProductsDto.class);

                    categoryProductsDto.setCategory(category.getName()) ;

                    categoryProductsDto.setProductsCount(category.getProducts().size());

                    categoryProductsDto.setAveragePrice(BigDecimal.valueOf(20));

                    BigDecimal sumOfProducts = BigDecimal.ZERO;
                    for (Product product : category.getProducts()) {
                        sumOfProducts = sumOfProducts.add(product.getPrice());
                    }

                    categoryProductsDto.setTotalRevenue(sumOfProducts);

                    return categoryProductsDto;

                })
                .collect(Collectors.toList());
    }
}
