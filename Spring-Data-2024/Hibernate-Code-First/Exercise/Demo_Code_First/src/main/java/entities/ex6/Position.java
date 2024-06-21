package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "positions")
public class Position extends BaseEntity {
    @Column
    private String description;
}
