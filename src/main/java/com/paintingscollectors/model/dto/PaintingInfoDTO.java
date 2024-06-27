package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.Painting;

public class PaintingInfoDTO {
    private long id;
    private String name;

    private String author;

    private String owner;

    private String imageUrl;

    public PaintingInfoDTO() {
    }

    public PaintingInfoDTO(Painting painting) {
        this.id = painting.getId();
        this.name = painting.getName();
        this.author = painting.getAuthor();
        this.owner = painting.getOwner().getUsername();
        this.imageUrl = painting.getImageUrl();

    }

    public long getId() {
        return id;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
