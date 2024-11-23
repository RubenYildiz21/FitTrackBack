package com.fittrack.fit_track.dto;

import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.PartieCorps;

import lombok.Data;

@Data
public class BlocExerciceDTO {
    private Long idExercice;
    private PartieCorps partieCorps;
    private Equipement equipementNecessaire;
} 