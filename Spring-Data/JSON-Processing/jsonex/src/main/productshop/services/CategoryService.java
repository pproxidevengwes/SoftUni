package com.example.jsonex.productshop.services;

import com.example.jsonex.productshop.model.dto.CategoryProductsDto;
import com.example.jsonex.productshop.model.entity.Category;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> findRandomCategories();

    List<CategoryProductsDto> findAllCategoriesWithProductCount();
}
