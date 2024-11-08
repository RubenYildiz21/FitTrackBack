package com.fittrack.fit_track.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fittrack.fit_track.model.Exercice;
import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.PartieCorps;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long> {

    List<Exercice> findByEquipementNecessaire(Equipement equipement);

    List<Exercice> findByPartieCorps(PartieCorps partieCorps);
}