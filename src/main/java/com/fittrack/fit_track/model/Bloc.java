package com.fittrack.fit_track.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;
    
    private Integer reps; // Renommé de 'repetition' à 'reps' pour cohérence
    private Integer serie;
    private String tempsRepos;
    private String tempsDeRepetition;
    private Double poids;

    private Double caloriesBurned;
    private Double distance;

    @ManyToOne
    @JoinColumn(name = "idExercice")
    private Exercice exercice;

    @ManyToOne
    @JoinColumn(name = "idSeance")
    private Seance seance;

    // Getters and Setters

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

    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
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
