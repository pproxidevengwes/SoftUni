package org.productshop.service;

import org.productshop.service.dtos.export.CategoriesByProductsDto;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void seedCategories() throws IOException;

    List<CategoriesByProductsDto> getAllCategoriesByProducts();

    void printAllCategoriesByProducts();
}
