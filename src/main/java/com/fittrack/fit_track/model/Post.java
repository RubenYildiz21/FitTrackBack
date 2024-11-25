package com.fittrack.fit_track.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    
    private String contenu;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation = new Date();

    private String imageUrl;

    private int nombreLikes = 0;


    // Relations
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commentaire> commentaires;



    // Getters et setters 
    public int getNombreLikes() {
        return nombreLikes;
    }

    public void setNombreLikes(int nombreLikes) {
        this.nombreLikes = nombreLikes;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContenu() {
        return contenu;
    }

    // Setter pour contenu
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}