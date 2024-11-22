// src/main/java/com/fittrack/fit_track/dto/DefiDTO.java
package com.fittrack.fit_track.dto;

import java.util.List;

public class DefiDTO {
    private Long idDefi;
    private String objectif;
    private Long createurId;
    private String createurFirstName;
    private String createurLastName;
    private List<Long> participantsIds;

    // Getters et Setters

    public Long getIdDefi() {
        return idDefi;
    }

    public void setIdDefi(Long idDefi) {
        this.idDefi = idDefi;
    }

    public String getObjectif() {
        return objectif;
    }
    
    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public Long getCreateurId() {
        return createurId;
    }
    
    public void setCreateurId(Long createurId) {
        this.createurId = createurId;
    }

    public String getCreateurFirstName() {
        return createurFirstName;
    }
    
    public void setCreateurFirstName(String createurFirstName) {
        this.createurFirstName = createurFirstName;
    }

    public String getCreateurLastName() {
        return createurLastName;
    }
    
    public void setCreateurLastName(String createurLastName) {
        this.createurLastName = createurLastName;
    }

    public List<Long> getParticipantsIds() {
        return participantsIds;
    }
    
    public void setParticipantsIds(List<Long> participantsIds) {
        this.participantsIds = participantsIds;
    }
}
