package entities.ex3;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Information {
    @Column(nullable = false)
    private String email;
    @Column(name = "price_per_hour", nullable = false)
    private BigDecimal pricePerHour;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;
}
