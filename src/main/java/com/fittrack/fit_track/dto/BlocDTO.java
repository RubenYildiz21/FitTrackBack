package com.fittrack.fit_track.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlocDTO {
    private Long idBloc;
    private Double caloriesBurned;
    private Double distance;
    private ExerciceDTO exercice;
    private Long seanceId;
    private List<SeriesDTO> series;
}
