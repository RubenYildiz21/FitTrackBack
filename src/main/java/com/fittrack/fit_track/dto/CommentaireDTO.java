// src/main/java/com/fittrack/fit_track/dto/CommentaireDTO.java
package com.fittrack.fit_track.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentaireDTO {

    @Getter
    @Setter
    private Long idCommentaire;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private Date dateCommentaire;

    @Getter
    @Setter
    private Long postId;

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private String userFirstName;

    @Getter
    @Setter
    private String userLastName;

    @Getter
    @Setter
    private String userProfilePicture; 
}
