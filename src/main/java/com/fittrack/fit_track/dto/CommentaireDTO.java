// src/main/java/com/fittrack/fit_track/dto/CommentaireDTO.java
package com.fittrack.fit_track.dto;

import java.util.Date;

public class CommentaireDTO {
    private Long idCommentaire;
    private String message;
    private Date dateCommentaire;
    private Long postId;
    private Long userId;
    private String userFirstName;
    private String userLastName;

    // Getters et Setters

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

    public Long getPostId() {
        return postId;
    }
    
    public void setPostId(Long postId) {
        this.postId = postId;
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
}
