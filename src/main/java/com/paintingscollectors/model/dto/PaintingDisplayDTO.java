package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.StyleType;

public class PaintingDisplayDTO {
    private long id;

    private String name;

    private String author;

    private StyleType style;

    private String ImageUrl;

    private String ownerName;

    private int votes;


    public PaintingDisplayDTO(Painting painting) {
        this.id = painting.getId();
        this.name = painting.getName();
        this.author = painting.getAuthor();
        this.style = painting.getStyle().getName();
        this.ImageUrl = painting.getImageUrl();
        this.ownerName = painting.getOwner().getUsername();
        this.votes = painting.getVotes();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public StyleType getStyle() {
        return style;
    }

    public void setStyle(StyleType style) {
        this.style = style;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
