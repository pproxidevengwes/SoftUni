package entities.ex6;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private Game game;
    @Id
    @ManyToOne
    @JoinColumn
    private Player player;
    @Column(name = "scored_goals")
    private short scoredGoals;
    @Column(name = "player_assists")
    private short playerAssists;
    @Column(name = "played_minutes_during_game")
    private short playedMinutesDuringGame;
}
