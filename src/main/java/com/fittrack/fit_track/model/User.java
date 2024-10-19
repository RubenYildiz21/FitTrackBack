package com.fittrack.fit_track.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    
    private String pseudo;
    private String prenom;
    private String nom;
    
    @Column(unique = true)
    private String mail;
    
    private Integer age;
    private Double taille;
    private Double poids;

    // Getters and Setters
}