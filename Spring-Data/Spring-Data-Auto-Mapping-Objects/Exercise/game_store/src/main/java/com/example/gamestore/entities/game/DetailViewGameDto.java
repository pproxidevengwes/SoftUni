package com.example.gamestore.entities.game;

import java.time.LocalDate;

public class DetailViewGameDto {
    private String title;
    private Double price;
    private String description;
    private LocalDate releaseDate;

    public DetailViewGameDto() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\n" +
                "Price: %.2f \n" +
                "Description: %s \n" +
                "Release date: %s", this.title, this.price, this.description, this.releaseDate);
    }
}
