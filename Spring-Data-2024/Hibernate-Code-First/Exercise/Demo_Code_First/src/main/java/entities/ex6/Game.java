package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {
    @OneToOne
    @JoinColumn
    private Team homeTeam;
    @OneToOne
    @JoinColumn
    private Team awayTeam;
    @Column(name = "home_goals")
    private short homeGoals;
    @Column(name = "away_goals")
    private short awayGoals;
    @Column(name = "date_and_time_of_game")
    private LocalDateTime dateAndTimeOfGame;
    @Column(name = "home_team_win_bet_rate")
    private double homeTeamWinBetRate;
    @Column(name = "away_team_win_bet_rate")
    private double awayTeamWinBetRate;
    @Column(name = "draw_gamebet_rate")
    private double drawGameBetRate;
    @ManyToOne
    @JoinColumn
    private Round round;
    @ManyToOne
    @JoinColumn
    private Competition competition;
}
