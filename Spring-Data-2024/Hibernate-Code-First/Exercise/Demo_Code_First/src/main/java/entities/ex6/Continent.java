package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "continents")
public class Continent extends BaseEntity {
}
