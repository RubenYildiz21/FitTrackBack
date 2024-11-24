// src/main/java/com/fittrack/fit_track/dto/RegisterResponseDTO.java
package com.fittrack.fit_track.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    private UserDTO user;
    private String profilePictureUrl;
}
