// src/main/java/com/fittrack/fit_track/dto/RegisterResponseDTO.java
package com.fittrack.fit_track.dto;

import lombok.Data;

@Data
public class RegisterResponseDTO {
    private UserDTO user;
    private String profilePictureUrl;
}
