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
}