package com.fittrack.fit_track.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesDTO {
    private Integer serie;
    private Integer reps;
    private Double poids;
    private String tempsDeRepetition;
    private String tempsRepos;
}
