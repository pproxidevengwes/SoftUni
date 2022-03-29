package com.example.cardealer.services;

import com.example.cardealer.model.dto.SaleDiscountCustomerDto;
import com.example.cardealer.model.entity.Sale;

import java.util.List;

public interface SaleService {
    void seedSales();

    List<Sale> getAllSales();

    List<SaleDiscountCustomerDto> getAllSalesWithDiscount();
}
