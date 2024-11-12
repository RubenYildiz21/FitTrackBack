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

    @PostMapping
    public ResponseEntity<?> enregistrerSeance(@Valid @RequestBody Seance seance) {
        try {
            Seance nouvelleSeance = seanceService.enregistrerSeance(seance);
            return ResponseEntity.ok(nouvelleSeance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeance(@PathVariable Long id) {
        return seanceService.getSeance(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/exercices/partie-corps/{partieCorps}")
    public ResponseEntity<List<Exercice>> filtrerExercicesParPartieCorps(
            @PathVariable PartieCorps partieCorps) {
        return ResponseEntity.ok(seanceService.filtrerExercices(partieCorps));
    }

    @GetMapping("/exercices/equipement/{equipement}")
    public ResponseEntity<List<Exercice>> filtrerExercicesParEquipement(
            @PathVariable Equipement equipement) {
        return ResponseEntity.ok(seanceService.filtrerExercices(equipement));
    }
} 