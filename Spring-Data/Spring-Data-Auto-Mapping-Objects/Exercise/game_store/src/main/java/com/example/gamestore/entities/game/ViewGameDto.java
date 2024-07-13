package com.example.gamestore.entities.game;

public class ViewGameDto {
    private String title;
    private Double price;

    public ViewGameDto() {
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

    @Override
    public String toString() {
        return String.format("%s %.2f", this.getTitle(), this.getPrice());
    }
}
