// src/main/java/com/fittrack/fit_track/dto/SeanceDTO.java
package com.fittrack.fit_track.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class SeanceDTO {
    private Long idSeance;
    private LocalDateTime dateSeance;
    private Long userId;
    private List<BlocDTO> blocs;
}
