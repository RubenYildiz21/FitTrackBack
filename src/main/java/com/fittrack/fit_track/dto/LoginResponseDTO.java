// src/main/java/com/fittrack/fit_track/dto/LoginResponseDTO.java
package com.fittrack.fit_track.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private UserDTO user;
}
