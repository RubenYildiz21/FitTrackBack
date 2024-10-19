package com.fittrack.fit_track.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Exercice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExercice;
    
    private String description;
    private String nom;
    private String lienVideo;
    private String materiel;

    // Relations
    @ManyToMany(mappedBy = "exercices")
    private List<Bloc> blocs;

    // Getters and Setters
}