// src/main/java/com/fittrack/fit_track/dto/RegisterResponseDTO.java
package com.fittrack.fit_track.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {

    @Getter
    @Setter
    private UserDTO user;

    @Getter
    @Setter
    private String profilePictureUrl;
}
