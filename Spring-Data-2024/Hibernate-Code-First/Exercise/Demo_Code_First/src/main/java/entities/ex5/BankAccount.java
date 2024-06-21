package entities.ex5;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BillingDetails{
    @Column
    private int number;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CardUser user;
}
