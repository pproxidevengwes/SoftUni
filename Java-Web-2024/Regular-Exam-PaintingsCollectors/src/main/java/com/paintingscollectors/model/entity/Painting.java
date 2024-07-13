package com.paintingscollectors.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "paintings")
public class Painting extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;

    @ManyToOne(optional = false)
    private Style style;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;
    @Column(nullable = false)
    private boolean isFavourite;
    @Column(nullable = false)
    private int votes;


    public Painting() {
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

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
