package org.modelmapperexercise.service.dtos;

public class CartItemDto {
    private String title;

    public CartItemDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
