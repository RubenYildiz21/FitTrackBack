package com.fittrack.fit_track.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.dto.ExerciceDTO;
import com.fittrack.fit_track.dto.SeanceDTO;
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
    public ResponseEntity<?> enregistrerSeance(@Valid @RequestBody SeanceDTO seanceDTO) {
        try {
            SeanceDTO nouvelleSeanceDTO = seanceService.enregistrerSeance(seanceDTO);
            return ResponseEntity.ok(nouvelleSeanceDTO);
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
        Optional<SeanceDTO> seanceOpt = seanceService.getSeance(id);
        if (seanceOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seance not found");
        }
        return ResponseEntity.ok(seanceOpt.get());
    }

    /**
     * Filtrer les exercices par partie du corps.
     *
     * @param partieCorps la partie du corps à filtrer
     * @return ResponseEntity contenant les exercices filtrés
     */
    @GetMapping("/exercices")
    public ResponseEntity<List<ExerciceDTO>> filtrerExercices(
            @RequestParam(required = false) PartieCorps partieCorps,
            @RequestParam(required = false) Equipement equipement) {

        if (partieCorps != null) {
            List<ExerciceDTO> exerciceDTOs = seanceService.filtrerExercices(partieCorps);
            return ResponseEntity.ok(exerciceDTOs);
        } else if (equipement != null) {
            List<ExerciceDTO> exerciceDTOs = seanceService.filtrerExercices(equipement);
            return ResponseEntity.ok(exerciceDTOs);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
} 