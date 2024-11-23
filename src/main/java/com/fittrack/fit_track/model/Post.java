package com.fittrack.fit_track.model;

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

    // Relations
    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commentaire> commentaires;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    // Getters and Setters
    
    // Getter pour contenu
    public String getContenu() {
        return contenu;
    }

    // Setter pour contenu
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    // Getters et setters pour les autres attributs et relations
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

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}