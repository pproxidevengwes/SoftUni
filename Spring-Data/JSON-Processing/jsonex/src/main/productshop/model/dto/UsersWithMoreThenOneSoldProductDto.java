package com.example.jsonex.productshop.model.dto;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UsersWithMoreThenOneSoldProductDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;

    @Expose
    private List<ProductsStatsDto> products;


    public UsersWithMoreThenOneSoldProductDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ProductsStatsDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsStatsDto> products) {
        this.products = products;
    }
}
