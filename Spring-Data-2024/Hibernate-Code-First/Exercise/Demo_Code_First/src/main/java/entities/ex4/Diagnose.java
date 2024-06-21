package entities.ex4;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "diagnoses")
public class Diagnose extends BaseEntity {
    @Column
    private String name;
    @Column
    private String comment;

    @OneToMany(mappedBy = "diagnose")
    private Set<Visitation> visitations;
    @ManyToMany
    @JoinTable(name = "diagnoses_medicaments", joinColumns = @JoinColumn(name = "diagose_id", referencedColumnName = "id"))
    private Set<Medicament> medicaments;
}
