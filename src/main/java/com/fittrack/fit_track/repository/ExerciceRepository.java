package com.fittrack.fit_track.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fittrack.fit_track.model.Exercice;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
}