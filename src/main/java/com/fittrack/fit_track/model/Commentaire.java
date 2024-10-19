package com.fittrack.fit_track.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommentaire;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommentaire = new Date();
    
    private String message;
    
    @ManyToOne
    @JoinColumn(name = "idCommentaireParent")
    private Commentaire parentCommentaire;

    // Relations
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // Getters and Setters
}