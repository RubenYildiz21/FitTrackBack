package com.fittrack.fit_track.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.model.Exercice;
import com.fittrack.fit_track.model.Seance;
import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.PartieCorps;
import com.fittrack.fit_track.service.SeanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/seances")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class SeanceController {

    @Autowired
    private SeanceService seanceService;

    /**
     * Creates a new workout session.
     *
     * @param seance the workout session to be created
     * @return ResponseEntity containing the created session or an error message
     */
    @PostMapping
    public ResponseEntity<?> enregistrerSeance(@Valid @RequestBody Seance seance) {
        try {
            Seance nouvelleSeance = seanceService.enregistrerSeance(seance);
            return ResponseEntity.ok(nouvelleSeance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /**
     * Retrieves a workout session by its ID.
     *
     * @param id the ID of the workout session to retrieve
     * @return ResponseEntity containing the found session or a not found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getSeance(@PathVariable Long id) {
        return seanceService.getSeance(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Filtrer les exercices par partie du corps.
     *
     * @param partieCorps la partie du corps à filtrer
     * @return ResponseEntity contenant les exercices filtrés
     */
    @GetMapping("/exercices")
    public ResponseEntity<List<Exercice>> filtrerExercices(
            @RequestParam(required = false) PartieCorps partieCorps,
            @RequestParam(required = false) Equipement equipement) {

        if (partieCorps != null) {
            return ResponseEntity.ok(seanceService.filtrerExercices(partieCorps));
        } else if (equipement != null) {
            return ResponseEntity.ok(seanceService.filtrerExercices(equipement));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
} 