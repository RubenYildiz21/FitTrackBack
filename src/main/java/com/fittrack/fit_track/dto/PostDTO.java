// src/main/java/com/fittrack/fit_track/dto/PostDTO.java
package com.fittrack.fit_track.dto;

import java.util.Date;
import java.util.List;

public class PostDTO {
    private Long idPost;
    private String contenu;
    private Date dateCreation;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private List<CommentaireDTO> commentaires;

    // Getters et Setters

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getContenu() {
        return contenu;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateCreation() {
        return dateCreation;
    }
    
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }
    
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }
    
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public List<CommentaireDTO> getCommentaires() {
        return commentaires;
    }
    
    public void setCommentaires(List<CommentaireDTO> commentaires) {
        this.commentaires = commentaires;
    }
}
