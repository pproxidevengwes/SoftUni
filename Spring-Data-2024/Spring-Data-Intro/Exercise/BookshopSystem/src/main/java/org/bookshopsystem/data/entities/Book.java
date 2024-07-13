package org.bookshopsystem.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Description;
import org.bookshopsystem.data.entities.base.BaseEntity;
import org.bookshopsystem.data.entities.enums.AgeRestriction;
import org.bookshopsystem.data.entities.enums.EditionType;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    @Column(length = 50, nullable = false)
    @NotNull(message = "Title cannot be null, enter value")
    private String title;
    @Column(length = 1000)
    @Length(min = 1, max = 100, message = "Description is not in valid length range")
    private String description;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "edition_type")
    private EditionType editionType;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    @Min(value = 20)
    @Max(value = 5000)
    @NotNull
    private int copies;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "age_restriction")
    private AgeRestriction ageRestriction;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "books_catogories",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;

    public Book(String title, EditionType editionType, BigDecimal price, int copies, LocalDate release_date, AgeRestriction ageRestriction, Author author, Set<Category> categories) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = release_date;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
    }

    public Book() {

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCopies() {
        return copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
