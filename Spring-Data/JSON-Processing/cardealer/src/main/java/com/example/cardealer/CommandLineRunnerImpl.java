package com.example.cardealer;

import com.example.cardealer.constants.GlobalApplicationConstants;
import com.example.cardealer.model.dto.*;
import com.example.cardealer.services.*;
import com.example.jsonex.cardealer.model.dto.*;
import com.example.jsonex.cardealer.services.*;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final BufferedReader reader;
    private final Gson gson;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public CommandLineRunnerImpl(Gson gson, SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.gson = gson;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        while (true) {
            System.out.println("Enter query number from 1 to 6:");
            int taskNumber = Integer.parseInt(reader.readLine());

            switch (taskNumber) {
                case 1 -> q1OrderedCustomers();
                case 2 -> q2CarsFromMakeToyota();
                case 3 -> q3LocalSuppliers();
                case 4 -> q4CarsAndParts();
                case 5 -> q5CustomerTotalSales();
                case 6 -> q6SalesWithAppliedDiscount();
                default -> System.out.println("Invalid number!");
            }
        }
    }

    private void q6SalesWithAppliedDiscount() throws IOException {
        List<SaleDiscountCustomerDto> saleDiscountCustomerDtos = saleService.getAllSalesWithDiscount();

        String content = gson.toJson(saleDiscountCustomerDtos);

        writeToFile((GlobalApplicationConstants.FILE_PATH_OUT + GlobalApplicationConstants.SALES_DISCOUNTS), content);
    }

    private void q5CustomerTotalSales() throws IOException {
        List<CustomerTotalSalesDto> customerTotalSalesDtos = customerService.getCustomersWithTheirSales();

        String content = gson.toJson(customerTotalSalesDtos);

        writeToFile((GlobalApplicationConstants.FILE_PATH_OUT + GlobalApplicationConstants.CUSTOMERS_TOTAL_SALES), content);
    }

    private void q4CarsAndParts() throws IOException {
        List<CarsAndParts> carsAndPartsDtos = carService.getCarsAndParts();

        String content = gson.toJson(carsAndPartsDtos);

        writeToFile((GlobalApplicationConstants.FILE_PATH_OUT + GlobalApplicationConstants.CARS_AND_PARTS), content);
    }

    private void q3LocalSuppliers() throws IOException {
        List<LocalSuppliersDto> localSuppliersDtos = supplierService.getLocalSuppliers();

        String content = gson.toJson(localSuppliersDtos);

        writeToFile((GlobalApplicationConstants.FILE_PATH_OUT + GlobalApplicationConstants.LOCAL_SUPPLIERS), content);
    }

    private void q2CarsFromMakeToyota() throws IOException {
        List<CarsToyotaDto> carsToyotaDtos = carService.getCarsFromMakeToyota("Toyota");

        String content = gson.toJson(carsToyotaDtos);

        writeToFile((GlobalApplicationConstants.FILE_PATH_OUT + GlobalApplicationConstants.TOYOTA_CARS), content);
    }

    private void q1OrderedCustomers() throws IOException {
        List<CustomerOrderedCapitalDto> customerOrderedCapitalDtos = this.customerService.getOrderedCustomers();

        String content = gson.toJson(customerOrderedCapitalDtos);

        writeToFile(GlobalApplicationConstants.FILE_PATH_OUT + GlobalApplicationConstants.ORDERED_CUSTOMERS, content);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }

    private void seedData() throws IOException {
        this.supplierService.seedSuppliers();
        this.partService.seedParts();
        this.carService.seedCars();
        this.customerService.seedCustomers();
        this.saleService.seedSales();


    }
}
