package com.fittrack.fit_track.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fittrack.fit_track.model.Exercice;
import com.fittrack.fit_track.repository.ExerciceRepository;

@Service
public class ExerciceService {
    
    @Autowired
    private ExerciceRepository exerciceRepository;
    
    public List<Exercice> getAllExercices() {
        return exerciceRepository.findAll();
    }
    
    public Exercice createExercice(Exercice exercice) {
        return exerciceRepository.save(exercice);
    }
    
    public Exercice getExerciceById(Long id) {
        return exerciceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Exercice not found"));
    }
} 