package com.fittrack.fit_track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.dto.ProgressDTO;
import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.service.CustomUserDetailsService;
import com.fittrack.fit_track.service.ProgressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/progress")
@SecurityRequirement(name = "bearerAuth") // Assure que l'endpoint est sécurisé
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    

    @Operation(summary = "Obtenir les progrès de l'utilisateur", 
               description = "Récupère les statistiques d'avancement de l'utilisateur en fonction de la période spécifiée (weekly, monthly, yearly).")
    @GetMapping
    public ResponseEntity<ProgressDTO> getProgress(
            @Parameter(description = "Période de temps pour les statistiques (weekly, monthly, yearly)", example = "monthly")
            @RequestParam(name = "period", defaultValue = "weekly") String period,
            Authentication authentication) {
        String email = authentication.getName();
        UserDTO user = userDetailsService.getUserDTOByEmail(email); // Implémenter cette méthode
        Long userId = user.getId();

        ProgressDTO progress = progressService.getUserProgress(userId, period);
        return ResponseEntity.ok(progress);
    }
}
