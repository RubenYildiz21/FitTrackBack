// src/main/java/com/fittrack/fit_track/dto/DefiDTO.java
package com.fittrack.fit_track.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefiDTO {

    private Long idDefi;

    private String objectif;

    private Long createurId;

    private String createurFirstName;

    private String createurLastName;

    private List<Long> participantsIds;
}
