// src/main/java/com/fittrack/fit_track/dto/SeanceDTO.java
package com.fittrack.fit_track.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceDTO {

    private Long idSeance;

    private LocalDateTime dateSeance;

    private Long userId;

    private List<BlocDTO> blocs;

    private String nameSeance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public LocalDateTime getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(LocalDateTime dateSeance) {
        this.dateSeance = dateSeance;
    }

    public List<BlocDTO> getBlocs() {
        return blocs;
    }

    public void setBlocs(List<BlocDTO> blocs) {
        this.blocs = blocs;
    }

    public String getNameSeance() {
        return nameSeance;
    }

    public void setNameSeance(String nameSeance) {
        this.nameSeance = nameSeance;
    }
}
