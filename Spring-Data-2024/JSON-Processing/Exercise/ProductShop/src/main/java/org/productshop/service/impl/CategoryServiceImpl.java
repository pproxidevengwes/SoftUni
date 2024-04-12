package org.productshop.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.productshop.data.entities.Category;
import org.productshop.data.entities.Product;
import org.productshop.data.repositories.CategoryRepository;
import org.productshop.service.CategoryService;
import org.productshop.service.dtos.imports.CategorySeedDto;
import org.productshop.service.dtos.export.CategoriesByProductsDto;
import org.productshop.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String FILE_PATH = "src/main/resources/json/categories.json";
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() == 0) {
            CategorySeedDto[] categorySeedDtos = this.gson.fromJson(new FileReader(FILE_PATH), CategorySeedDto[].class);
            for (CategorySeedDto categorySeedDto : categorySeedDtos) {
                if (!this.validationUtil.isValid(categorySeedDto)) {
                    this.validationUtil.getViolations(categorySeedDto)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                Category category = this.modelMapper.map(categorySeedDto, Category.class);
                this.categoryRepository.saveAndFlush(category);

            }
        }
    }

    @Override
    public List<CategoriesByProductsDto> getAllCategoriesByProducts() {
        return this.categoryRepository.findCategoriesByProducts().stream()
                .map(c -> {
                    CategoriesByProductsDto dto = this.modelMapper.map(c, CategoriesByProductsDto.class);
                    dto.setProductsCount(c.getProducts().size());
                    BigDecimal sum = c.getProducts().stream().map(Product::getPrice).reduce(BigDecimal::add).get();
                    dto.setTotalRevenue(sum);
                    dto.setAveragePrice(sum.divide(BigDecimal.valueOf(c.getProducts().size()),  MathContext.DECIMAL32));
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void printAllCategoriesByProducts() {
        String json = this.gson.toJson(this.getAllCategoriesByProducts());
        System.out.println(json);
    }
}
