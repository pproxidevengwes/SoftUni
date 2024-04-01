package entities.ex5;

import entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BillingDetails extends BaseEntity {
    @Column
    private int number;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CardUser user;

}
