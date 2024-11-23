package com.fittrack.fit_track.dto;

import lombok.Data;

@Data
public class SeriesDTO {
    private Integer serie;
    private Integer reps;
    private Double poids;
    private String tempsDeRepetition;
    private String tempsRepos;
}
