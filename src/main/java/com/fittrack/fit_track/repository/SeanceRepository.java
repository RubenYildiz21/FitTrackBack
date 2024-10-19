package com.fittrack.fit_track.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fittrack.fit_track.model.Seance;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
}