package com.example.cardealer.model.dto;


import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerOrderedCapitalDto {
    @Expose
    private Long Id;

    @Expose
    private String Name;

    @Expose
    private LocalDateTime BirthDate;

    @Expose
    private Boolean IsYoungDriver;

    @Expose
    private List<SalesCarMakeModelDto> Sales;

    public CustomerOrderedCapitalDto() {
    }

    public List<SalesCarMakeModelDto> getSales() {
        return Sales;
    }

    public void setSales(List<SalesCarMakeModelDto> sales) {
        Sales = sales;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LocalDateTime getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        BirthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return IsYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        IsYoungDriver = youngDriver;
    }
}
