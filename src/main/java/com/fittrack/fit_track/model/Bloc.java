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
    
    private Integer repetition;
    private Integer serie;
    private String tempsRepos;
    private String tempsDeRepetition;
    private Double poids;

    @ManyToOne
    @JoinColumn(name = "idExercice", nullable = false)
    private Exercice exercice;

    // Getters and Setters

    public Long getIdBloc() {
        return idBloc;
    }

    public void setIdBloc(Long idBloc) {
        this.idBloc = idBloc;
    }

    public Integer getRepetition() {
        return repetition;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
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
}