// src/main/java/com/fittrack/fit_track/dto/SeanceDTO.java
package com.fittrack.fit_track.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceDTO {
    private Long idSeance;
    private LocalDateTime dateSeance;
    private Long userId;
    private List<BlocDTO> blocs;
    private String nameSeance;
}
