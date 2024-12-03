// src/main/java/com/fittrack/fit_track/dto/PostDTO.java
package com.fittrack.fit_track.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long idPost;

    private String contenu;

    private String imageUrl;

    private Date dateCreation;

    private Long userId;

    private String userFirstName;

    private String userLastName;

    private String userProfilePicture;

    private int nombreLikes;
}
