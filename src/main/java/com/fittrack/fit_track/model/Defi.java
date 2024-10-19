package com.fittrack.fit_track.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Defi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDefi;
    
    private String objectif;
    
    @ManyToOne
    @JoinColumn(name = "idCreateur")
    private User createur;  // Assurez-vous que la classe "Membre" existe

    // Relations
    @ManyToMany
    @JoinTable(name = "participer", joinColumns = @JoinColumn(name = "idDefi"), inverseJoinColumns = @JoinColumn(name = "idUser"))
    private List<User> participants;  // Assurez-vous que "Membre" est la classe appropriée

    // Getters and Setters
}