package com.example.cardealer.model.dto;

import com.google.gson.annotations.Expose;

public class SalesCarMakeModelDto {
    @Expose
    private String car;

    public SalesCarMakeModelDto() {
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
