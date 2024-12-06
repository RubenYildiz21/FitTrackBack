package com.fittrack.fit_track.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
public class Defi {
    // Getters and Setters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDefi;
    

    private String objectif;


    @ManyToOne
    @JoinColumn(name = "idCreateur")
    private User createur;

    // Relations

    @ManyToMany
    @JoinTable(name = "participer", joinColumns = @JoinColumn(name = "idDefi"), inverseJoinColumns = @JoinColumn(name = "idUser"))
    private List<User> participants;

    // Getters and Setters


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

    public User getCreateur() {
        return createur;
    }

    public void setCreateur(User createur) {
        this.createur = createur;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }


}