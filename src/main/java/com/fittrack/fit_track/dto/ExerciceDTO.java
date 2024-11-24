// src/main/java/com/fittrack/fit_track/dto/ExerciceDTO.java
package com.fittrack.fit_track.dto;

import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.ExerciseType;
import com.fittrack.fit_track.model.enums.PartieCorps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciceDTO {
    private Long idExercice;
    private String description;
    private String nom;
    private String lienVideo;
    private String materiel;
    private PartieCorps partieCorps;
    private Equipement equipementNecessaire;
    private ExerciseType type;
    private Double caloriesBurned;
    private Double distance;
}
