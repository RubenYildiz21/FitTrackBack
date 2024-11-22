package com.fittrack.fit_track.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.dto.DefiDTO;
import com.fittrack.fit_track.mapper.DefiMapper;
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
    public ResponseEntity<List<DefiDTO>> getAllDefis() {
        List<Defi> defis = defiRepository.findAll();
        List<DefiDTO> defiDTOs = defis.stream()
                                      .map(DefiMapper.INSTANCE::defiToDefiDTO)
                                      .toList();
        return ResponseEntity.ok(defiDTOs);
    }

    // Obtenir un défi par ID
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DefiDTO> getDefiById(@PathVariable Long id) {
        Optional<Defi> defiOpt = defiRepository.findById(id);
        if (defiOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DefiDTO defiDTO = DefiMapper.INSTANCE.defiToDefiDTO(defiOpt.get());
        return ResponseEntity.ok(defiDTO);
    }

    // Créer un nouveau défi
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DefiDTO> createDefi(@RequestBody DefiDTO defiDTO) {
        Defi defi = DefiMapper.INSTANCE.defiDTOToDefi(defiDTO);
        Defi savedDefi = defiRepository.save(defi);
        DefiDTO savedDefiDTO = DefiMapper.INSTANCE.defiToDefiDTO(savedDefi);
        return new ResponseEntity<>(savedDefiDTO, HttpStatus.CREATED);
    }

    // Mettre à jour un défi
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DefiDTO> updateDefi(@PathVariable Long id, @RequestBody DefiDTO defiDTO) {
        Optional<Defi> defiOpt = defiRepository.findById(id);
        if (defiOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Defi defi = DefiMapper.INSTANCE.defiDTOToDefi(defiDTO);
        defi.setIdDefi(id); // Assurez-vous de définir l'ID
        Defi updatedDefi = defiRepository.save(defi);
        DefiDTO updatedDefiDTO = DefiMapper.INSTANCE.defiToDefiDTO(updatedDefi);
        return ResponseEntity.ok(updatedDefiDTO);
    }

    // Supprimer un défi par ID
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteDefi(@PathVariable Long id) {
        Optional<Defi> defiOpt = defiRepository.findById(id);
        if (defiOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        defiRepository.delete(defiOpt.get());
        return ResponseEntity.noContent().build();
    }
}