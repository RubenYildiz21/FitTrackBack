package com.fittrack.fit_track.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeance;
    
    @Temporal(TemporalType.DATE)
    private Date dateSeance;

    // Relations : une séance peut contenir plusieurs blocs
    @OneToMany
    @JoinColumn(name = "idSeance")
    private List<Bloc> blocs; // La liste de blocs dans une séance

    // Getters and Setters
}