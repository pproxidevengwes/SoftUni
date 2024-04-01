package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity {
    @ManyToOne
    private CompetitionType type;
}
