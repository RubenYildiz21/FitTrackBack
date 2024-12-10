// src/main/java/com/fittrack/fit_track/dto/UserDTO.java
package com.fittrack.fit_track.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChallengeDTO {
    private Long challenge_id;
    private Long user_id;
    private Long user_score;
}
