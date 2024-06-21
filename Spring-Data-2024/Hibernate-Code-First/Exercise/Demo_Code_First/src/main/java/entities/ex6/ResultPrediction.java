package entities.ex6;

import entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private Prediction prediction;
}
