package com.paintingscollectors.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Painting> paintings;
    @ManyToMany()
    @JoinTable(name = "favourite_paintings",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "painting_id", referencedColumnName = "id"))
    private Set<Painting> favouritePaintings;
    @ManyToMany
    @JoinTable(name = "rated_paintings",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "painting_id", referencedColumnName = "id"))
    private Set<Painting> ratedPaintings;

    public User() {
        this.paintings = new ArrayList<>();
        this.favouritePaintings = new HashSet<>();
        this.ratedPaintings = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    public void setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
    }

    public Set<Painting> getFavouritePaintings() {
        return favouritePaintings;
    }

    public void setFavouritePaintings(Set<Painting> favouritePaintings) {
        this.favouritePaintings = favouritePaintings;
    }

    public Set<Painting> getRatedPaintings() {
        return ratedPaintings;
    }

    public void setRatedPaintings(Set<Painting> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
    }

    public void addFavourite(Painting painting) {
        this.favouritePaintings.add(painting);
    }
}
