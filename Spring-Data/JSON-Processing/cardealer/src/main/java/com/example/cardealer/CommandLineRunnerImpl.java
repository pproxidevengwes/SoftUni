package com.example.cardealer;

import com.example.cardealer.constants.GlobalApplicationConstants;
import com.example.cardealer.model.dto.*;
import com.example.cardealer.services.*;
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
    public static final String FILE_PATH_READ = "src/main/resources/files/";
    public static final String FILE_PATH_OUT = "src/main/resources/files/out/";
    public static final String SALES_DISCOUNTS = "sales-discounts.json";
    public static final String CUSTOMERS_TOTAL_SALES = "customers-total-sales.json";
    public static final String CARS_AND_PARTS = "cars-and-parts.json";
    public static final String LOCAL_SUPPLIERS = "local-suppliers.json";
    public static final String TOYOTA_CARS = "toyota-cars.json";
    public static final String ORDERED_CUSTOMERS = "ordered-customers.json";

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

        writeToFile((FILE_PATH_OUT + SALES_DISCOUNTS), content);
    }

    private void q5CustomerTotalSales() throws IOException {
        List<CustomerTotalSalesDto> customerTotalSalesDtos = customerService.getCustomersWithTheirSales();

        String content = gson.toJson(customerTotalSalesDtos);

        writeToFile((FILE_PATH_OUT + CUSTOMERS_TOTAL_SALES), content);
    }

    private void q4CarsAndParts() throws IOException {
        List<CarsAndParts> carsAndPartsDtos = carService.getCarsAndParts();

        String content = gson.toJson(carsAndPartsDtos);

        writeToFile((FILE_PATH_OUT + CARS_AND_PARTS), content);
    }

    private void q3LocalSuppliers() throws IOException {
        List<LocalSuppliersDto> localSuppliersDtos = supplierService.getLocalSuppliers();

        String content = gson.toJson(localSuppliersDtos);

        writeToFile((FILE_PATH_OUT + LOCAL_SUPPLIERS), content);
    }

    private void q2CarsFromMakeToyota() throws IOException {
        List<CarsToyotaDto> carsToyotaDtos = carService.getCarsFromMakeToyota("Toyota");

        String content = gson.toJson(carsToyotaDtos);

        writeToFile((FILE_PATH_OUT + TOYOTA_CARS), content);
    }

    private void q1OrderedCustomers() throws IOException {
        List<CustomerOrderedCapitalDto> customerOrderedCapitalDtos = this.customerService.getOrderedCustomers();

        String content = gson.toJson(customerOrderedCapitalDtos);

        writeToFile(FILE_PATH_OUT + ORDERED_CUSTOMERS, content);
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
