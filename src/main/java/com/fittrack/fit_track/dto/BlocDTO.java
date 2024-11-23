// src/main/java/com/fittrack/fit_track/dto/BlocDTO.java
package com.fittrack.fit_track.dto;

import java.util.List;

import lombok.Data;

@Data
public class BlocDTO {
    private Long idBloc;
    private Double caloriesBurned;
    private Double distance;
    private ExerciceDTO exercice;
    private Long seanceId;
    private List<SeriesDTO> series;
}
