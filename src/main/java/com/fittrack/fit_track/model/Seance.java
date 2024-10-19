package com.fittrack.fit_track.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeance;
    
    @Temporal(TemporalType.DATE)
    private Date dateSeance;

    // Relations : une séance peut contenir plusieurs blocs
    @OneToMany
    @JoinColumn(name = "idSeance")
    private List<Bloc> blocs;

    // Getters and Setters

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public Date getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(Date dateSeance) {
        this.dateSeance = dateSeance;
    }

    public List<Bloc> getBlocs() {
        return blocs;
    }

    public void setBlocs(List<Bloc> blocs) {
        this.blocs = blocs;
    }
}