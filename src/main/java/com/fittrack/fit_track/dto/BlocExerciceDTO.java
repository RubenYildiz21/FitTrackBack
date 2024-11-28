package com.fittrack.fit_track.dto;

import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.PartieCorps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlocExerciceDTO {

    @Getter
    @Setter
    private Long idExercice;

    @Getter
    @Setter
    private PartieCorps partieCorps;

    @Getter
    @Setter
    private Equipement equipementNecessaire;
} 