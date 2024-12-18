package com.fittrack.fit_track.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.dto.ExerciceDTO;
import com.fittrack.fit_track.service.ExerciceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/exercices")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Tag(name = "Exercices", description = "API pour gérer les exercices")
public class ExerciceController {

    @Autowired
    private ExerciceService exerciceService;

    @GetMapping
    @Operation(summary = "Récupérer tous les exercices")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des exercices récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ExerciceDTO>> getAllExercices() {
        try {
            List<ExerciceDTO> exercices = exerciceService.getAllExercices();
            return ResponseEntity.ok(exercices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un exercice par son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exercice trouvé"),
        @ApiResponse(responseCode = "404", description = "Exercice non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ExerciceDTO> getExerciceById(@PathVariable Long id) {
        try {
            ExerciceDTO exerciceDTO = exerciceService.getExerciceById(id);
            return ResponseEntity.ok(exerciceDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
} 