// src/main/java/com/fittrack/fit_track/dto/BlocDTO.java
package com.fittrack.fit_track.dto;

public class BlocDTO {
    private Long idBloc;
    private Integer reps;
    private Integer serie;
    private String tempsRepos;
    private String tempsDeRepetition;
    private Double poids;
    private Double caloriesBurned;
    private Double distance;
    private ExerciceDTO exercice;
    private Long seanceId;

    // Getters et Setters

    public Long getIdBloc() {
        return idBloc;
    }

    public void setIdBloc(Long idBloc) {
        this.idBloc = idBloc;
    }

    public Integer getReps() {
        return reps;
    }
    
    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getSerie() {
        return serie;
    }
    
    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public String getTempsRepos() {
        return tempsRepos;
    }
    
    public void setTempsRepos(String tempsRepos) {
        this.tempsRepos = tempsRepos;
    }

    public String getTempsDeRepetition() {
        return tempsDeRepetition;
    }
    
    public void setTempsDeRepetition(String tempsDeRepetition) {
        this.tempsDeRepetition = tempsDeRepetition;
    }

    public Double getPoids() {
        return poids;
    }
    
    public void setPoids(Double poids) {
        this.poids = poids;
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

    public ExerciceDTO getExercice() {
        return exercice;
    }
    
    public void setExercice(ExerciceDTO exercice) {
        this.exercice = exercice;
    }

    public Long getSeanceId() {
        return seanceId;
    }
    
    public void setSeanceId(Long seanceId) {
        this.seanceId = seanceId;
    }
}
