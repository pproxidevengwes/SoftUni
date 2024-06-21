package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {
    @Column
    private String name;
    @Column
    private String logo;
    @Column
    private String initials;

    @ManyToOne
    @JoinColumn(name = "primary_kit", referencedColumnName = "id")
    private Color primaryKit;
    @ManyToOne
    @JoinColumn(name = "secondary_kit", referencedColumnName = "id")
    private Color secondaryKit;
    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;
    @Column
    private double budget;

    public Team() {

    }

}
