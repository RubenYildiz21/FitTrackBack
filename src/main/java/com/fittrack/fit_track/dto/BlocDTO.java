// src/main/java/com/fittrack/fit_track/dto/BlocDTO.java
package com.fittrack.fit_track.dto;

import lombok.Data;

@Data
public class BlocDTO {
    private Long idBloc;
    private Integer reps;
    private Integer serie;
    private String tempsRepos;
    private String tempsDeRepetition;
    private Double poids;
    private Double caloriesBurned;
    private Double distance;
    private ExerciceDTO exercice;
    private Long seanceId;
}
