package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bets")
public class Bet extends BaseEntity {
    @Column(name = "bet_money")
    private BigDecimal betMoney;
    @Column(name = "time_of_bet")
    private LocalDateTime dateAndTimeOfBet;

    @ManyToOne
    private User user;
}
