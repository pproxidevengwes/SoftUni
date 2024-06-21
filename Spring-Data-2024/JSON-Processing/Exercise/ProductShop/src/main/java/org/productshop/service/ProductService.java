package org.productshop.service;

import org.productshop.service.dtos.export.ProductInRangeDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductInRangeDto> getAllProductsInRange(BigDecimal from, BigDecimal to);

    void printAllProductsInRange(BigDecimal from, BigDecimal to);

}
