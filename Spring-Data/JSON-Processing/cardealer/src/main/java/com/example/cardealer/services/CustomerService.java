package com.example.cardealer.services;

import com.example.cardealer.model.dto.CustomerOrderedCapitalDto;
import com.example.cardealer.model.dto.CustomerTotalSalesDto;
import com.example.cardealer.model.entity.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void seedCustomers() throws IOException;

    Customer getRandomCustomer();

    List<CustomerOrderedCapitalDto> getOrderedCustomers();

    List<CustomerTotalSalesDto> getCustomersWithTheirSales();
}
