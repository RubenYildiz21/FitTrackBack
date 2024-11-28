package com.fittrack.fit_track.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlocDTO {

    @Getter
    @Setter
    private Long idBloc;

    @Getter
    @Setter
    private Double caloriesBurned;

    @Getter
    @Setter
    private Double distance;

    @Getter
    @Setter
    private ExerciceDTO exercice;

    @Getter
    @Setter
    private Long seanceId;

    @Getter
    @Setter
    private List<SeriesDTO> series;
}
