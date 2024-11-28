// src/main/java/com/fittrack/fit_track/dto/DefiDTO.java
package com.fittrack.fit_track.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefiDTO {

    @Getter
    @Setter
    private Long idDefi;

    @Getter
    @Setter
    private String objectif;

    @Getter
    @Setter
    private Long createurId;

    @Getter
    @Setter
    private String createurFirstName;

    @Getter
    @Setter
    private String createurLastName;

    @Getter
    @Setter
    private List<Long> participantsIds;
}
