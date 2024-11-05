package com.fittrack.fit_track.dto;

import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.PartieCorps;

public class BlocExerciceDTO {
    private Long idExercice;
    private PartieCorps partieCorps;
    private Equipement equipementNecessaire;
    
    public Long getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(Long idExercice) {
        this.idExercice = idExercice;
    }

    public PartieCorps getPartieCorps() {
        return partieCorps;
    }

    public void setPartieCorps(PartieCorps partieCorps) {
        this.partieCorps = partieCorps;
    }

    public Equipement getEquipementNecessaire() {
        return equipementNecessaire;
    }

    public void setEquipementNecessaire(Equipement equipementNecessaire) {
        this.equipementNecessaire = equipementNecessaire;
    }
} 