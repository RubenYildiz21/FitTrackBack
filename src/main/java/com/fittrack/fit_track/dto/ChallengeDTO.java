// src/main/java/com/fittrack/fit_track/dto/UserDTO.java
package com.fittrack.fit_track.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDTO {
    private long id;
    private String title;
    private String description;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Long id_user;
    private String exercise;
}
