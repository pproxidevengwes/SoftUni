package entities.ex3;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Information {
    @Column(name = "average_grade")
    private double averageGrade;
    private int attendance;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}
