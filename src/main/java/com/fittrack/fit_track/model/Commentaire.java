package com.fittrack.fit_track.model;

import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.List;
import jakarta.persistence.CascadeType;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommentaire;
    
    private String message;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommentaire = new Date();
    
    @ManyToOne
    @JoinColumn(name = "idPost", nullable = false)
    private Post post;
    
    // Ajout du champ user pour la relation avec User
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;  // Utilisateur qui a fait le commentaire

    // Relation pour les réponses aux commentaires
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_comment_id")
    private Commentaire parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Commentaire> replies = new ArrayList<>();


    // Getters et setters
    public Commentaire getParentComment() {
        return parentComment;
    }

    public void setParentComment(Commentaire parentComment) {
        this.parentComment = parentComment;
    }
    
    public List<Commentaire> getReplies() {
        return replies;
    }

    public void setReplies(List<Commentaire> replies) {
        this.replies = replies;
    }




    public Long getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(Long idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}