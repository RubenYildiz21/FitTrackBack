// src/main/java/com/fittrack/fit_track/dto/LoginResponseDTO.java
package com.fittrack.fit_track.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private UserDTO user;
}
