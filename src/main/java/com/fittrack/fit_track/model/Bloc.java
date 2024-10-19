package com.fittrack.fit_track.model;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;
    
    private Integer repetition;
    private Integer serie;
    private LocalTime tempsRepos;
    private LocalTime tempsDeRepetition;
    private Double poids;

    // Relations : Un bloc contient plusieurs exercices
    @ManyToMany
    @JoinTable(name = "repeter", joinColumns = @JoinColumn(name = "idBloc"), inverseJoinColumns = @JoinColumn(name = "idExercice"))
    private List<Exercice> exercices;

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

    public LocalTime getTempsRepos() {
        return tempsRepos;
    }

    public void setTempsRepos(LocalTime tempsRepos) {
        this.tempsRepos = tempsRepos;
    }

    public LocalTime getTempsDeRepetition() {
        return tempsDeRepetition;
    }

    public void setTempsDeRepetition(LocalTime tempsDeRepetition) {
        this.tempsDeRepetition = tempsDeRepetition;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(List<Exercice> exercices) {
        this.exercices = exercices;
    }
}