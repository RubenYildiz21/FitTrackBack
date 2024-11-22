// src/main/java/com/fittrack/fit_track/dto/ExerciceDTO.java
package com.fittrack.fit_track.dto;

import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.ExerciseType;
import com.fittrack.fit_track.model.enums.PartieCorps;

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

    // Getters et Setters

    public Long getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(Long idExercice) {
        this.idExercice = idExercice;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLienVideo() {
        return lienVideo;
    }
    
    public void setLienVideo(String lienVideo) {
        this.lienVideo = lienVideo;
    }

    public String getMateriel() {
        return materiel;
    }
    
    public void setMateriel(String materiel) {
        this.materiel = materiel;
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

    public ExerciseType getType() {
        return type;
    }
    
    public void setType(ExerciseType type) {
        this.type = type;
    }

    public Double getCaloriesBurned() {
        return caloriesBurned;
    }
    
    public void setCaloriesBurned(Double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Double getDistance() {
        return distance;
    }
    
    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
