package entities.ex6;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "bet_game")
public class BetGame implements Serializable {
    @Id
    @OneToOne
    private Game game;
    @Id
    @OneToOne
    private Bet bet;
    @OneToOne
    @JoinColumn
    private ResultPrediction resultPrediction;

    public BetGame() {
    }
}
