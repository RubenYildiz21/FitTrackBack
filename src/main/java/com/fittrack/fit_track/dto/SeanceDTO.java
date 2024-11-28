// src/main/java/com/fittrack/fit_track/dto/SeanceDTO.java
package com.fittrack.fit_track.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceDTO {

    @Getter
    @Setter
    private Long idSeance;

    @Getter
    @Setter
    private LocalDateTime dateSeance;

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private List<BlocDTO> blocs;

    @Getter
    @Setter
    private String nameSeance;
}
