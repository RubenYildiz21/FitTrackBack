// src/main/java/com/fittrack/fit_track/dto/ExerciceDTO.java
package com.fittrack.fit_track.dto;

import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.ExerciseType;
import com.fittrack.fit_track.model.enums.PartieCorps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciceDTO {

    @Getter
    @Setter
    private Long idExercice;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String nom;

    @Getter
    @Setter
    private String lienVideo;

    @Getter
    @Setter
    private String materiel;

    @Getter
    @Setter
    private PartieCorps partieCorps;

    @Getter
    @Setter
    private Equipement equipementNecessaire;

    @Getter
    @Setter
    private ExerciseType type;

    @Getter
    @Setter
    private Double caloriesBurned;

    @Getter
    @Setter
    private Double distance;
}
