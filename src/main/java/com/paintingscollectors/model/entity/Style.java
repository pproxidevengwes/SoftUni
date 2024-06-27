package com.paintingscollectors.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleType name;
    @Column(nullable = false)
    private String description;

    public Style(StyleType name, String description) {
        this.name = name;
        this.description = description;
    }

    public Style() {

    }

    public StyleType getName() {
        return name;
    }

    public void setName(StyleType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
