package com.fittrack.fit_track.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.model.Defi;
import com.fittrack.fit_track.repository.DefiRepository;

@RestController
@RequestMapping("/api/defis")
public class DefiController {

    @Autowired
    private DefiRepository defiRepository;

    // Obtenir la liste de tous les défis
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Defi> getAllDefis() {
        return defiRepository.findAll();
    }

    // Obtenir un défi par ID
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Optional<Defi> getDefiById(@PathVariable Long id) {
        return defiRepository.findById(id);
    }

    // Créer un nouveau défi
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public Defi createDefi(@RequestBody Defi defi) {
        return defiRepository.save(defi);
    }

    // Mettre à jour un défi
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Defi updateDefi(@PathVariable Long id, @RequestBody Defi defiDetails) {
        Defi defi = defiRepository.findById(id).orElseThrow(() -> new RuntimeException("Defi not found"));

        defi.setObjectif(defiDetails.getObjectif());
        defi.setCreateur(defiDetails.getCreateur());
        defi.setParticipants(defiDetails.getParticipants());

        return defiRepository.save(defi);
    }

    // Supprimer un défi par ID
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteDefi(@PathVariable Long id) {
        Defi defi = defiRepository.findById(id).orElseThrow(() -> new RuntimeException("Defi not found"));
        defiRepository.delete(defi);
    }
}