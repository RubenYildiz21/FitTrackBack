package com.fittrack.fit_track.model;

import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.ExerciseType;
import com.fittrack.fit_track.model.enums.PartieCorps;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Exercice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExercice;
    
    private String description;
    private String nom;
    private String lienVideo;
    private String materiel;

    @Enumerated(EnumType.STRING)
    private PartieCorps partieCorps;

    @Enumerated(EnumType.STRING)
    private Equipement equipementNecessaire;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ExerciseType type;

    private Double caloriesBurned;
    private Double distance; 


    // Getters and Setters

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

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }
}