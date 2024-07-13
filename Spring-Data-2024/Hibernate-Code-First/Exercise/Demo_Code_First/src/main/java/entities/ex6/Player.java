package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {
    @Column(name = "squad_number")
    private short squadNumber;

    @ManyToOne
    private Team team;
    @ManyToOne
    private Position position;
    @Column(name = "is_currently_injured")
    private boolean isCurrentlyInjured;
}
