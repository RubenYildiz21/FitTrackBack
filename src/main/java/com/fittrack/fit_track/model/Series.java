package com.fittrack.fit_track.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeries;
    
    private Integer serie;
    private Integer reps;
    private Double poids;
    private String tempsDeRepetition;
    private String tempsRepos;
    
    @ManyToOne
    @JoinColumn(name = "idBloc")
    private Bloc bloc;
    
    private Double caloriesBurned = 0.0;
    private Double distance = 0.0;
    
    // Getters and Setters

    public Long getIdSeries() {
        return idSeries;
    }

    public void setIdSeries(Long idSeries) {
        this.idSeries = idSeries;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public String getTempsDeRepetition() {
        return tempsDeRepetition;
    }

    public void setTempsDeRepetition(String tempsDeRepetition) {
        this.tempsDeRepetition = tempsDeRepetition;
    }

    public String getTempsRepos() {
        return tempsRepos;
    }

    public void setTempsRepos(String tempsRepos) {
        this.tempsRepos = tempsRepos;
    }

    public Bloc getBloc() {
        return bloc;
    }

    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
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
