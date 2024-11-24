// src/main/java/com/fittrack/fit_track/dto/CommentaireDTO.java
package com.fittrack.fit_track.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentaireDTO {
    private Long idCommentaire;
    private String message;
    private Date dateCommentaire;
    private Long postId;
    private Long userId;
    private String userFirstName;
    private String userLastName;
}
