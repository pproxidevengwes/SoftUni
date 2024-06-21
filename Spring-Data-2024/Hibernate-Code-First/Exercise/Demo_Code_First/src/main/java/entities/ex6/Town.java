package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {
    @ManyToOne
    private Country country;
}
