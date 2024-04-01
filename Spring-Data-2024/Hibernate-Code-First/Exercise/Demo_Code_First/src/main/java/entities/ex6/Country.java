package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {
    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents",
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"))
    private Set<Continent> continent;

}
