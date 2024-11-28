package com.fittrack.fit_track.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesDTO {

    @Getter
    @Setter
    private Integer serie;

    @Getter
    @Setter
    private Integer reps;

    @Getter
    @Setter
    private Double poids;

    @Getter
    @Setter
    private String tempsDeRepetition;

    @Getter
    @Setter
    private String tempsRepos;

    @Getter
    @Setter
    private Double caloriesBurned;

    @Getter
    @Setter
    private Double distance;
}
