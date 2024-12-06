package com.fittrack.fit_track.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUser;
    private String title;
    private String exercise;
    private LocalDate beginingDate;
    private LocalDate endingDate;
    private String description;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public LocalDate getBeginingDate() {
        return beginingDate;
    }

    public void setBeginingDate(LocalDate beginingDate) {
        this.beginingDate = beginingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String Description) {
        this.description = Description;
    }
}
