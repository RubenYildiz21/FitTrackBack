// src/main/java/com/fittrack/fit_track/dto/PostDTO.java
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
public class PostDTO {

    @Getter
    @Setter
    private Long idPost;

    @Getter
    @Setter
    private String contenu;

    @Getter
    @Setter
    private String imageUrl;

    @Getter
    @Setter
    private Date dateCreation;

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

    @Getter
    @Setter
    private int nombreLikes;
}
