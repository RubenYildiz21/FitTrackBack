package com.fittrack.fit_track.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    
    private String contenu;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation = new Date();

    // Relations
    @OneToMany(mappedBy = "post")
    private List<Commentaire> commentaires;

    // Getters and Setters
}