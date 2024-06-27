package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.StyleType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPaintingDTO {
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    @NotNull
    private String name;
    @Size(min = 5, max = 30, message = "Author length must be between 5 and 30 characters!")
    @NotNull
    private String author;
    @NotNull(message = "Please enter valid image URL!")
    @Size(max = 150, message = "The field must be less than 150 characters!")
    private String imageUrl;
    @NotNull(message = "You must select a style!")
    private StyleType style;

    public AddPaintingDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public StyleType getStyle() {
        return style;
    }

    public void setStyle(StyleType style) {
        this.style = style;
    }
}
