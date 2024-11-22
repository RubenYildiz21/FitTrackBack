package com.fittrack.fit_track.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fittrack.fit_track.dto.ExerciceDTO;
import com.fittrack.fit_track.mapper.ExerciceMapper;
import com.fittrack.fit_track.model.Exercice;
import com.fittrack.fit_track.repository.ExerciceRepository;

@Service
public class ExerciceService {
    
    @Autowired
    private ExerciceRepository exerciceRepository;
    
    public List<ExerciceDTO> getAllExercices() {
        List<Exercice> exercices = exerciceRepository.findAll();
        return exercices.stream()
                        .map(ExerciceMapper.INSTANCE::exerciceToExerciceDTO)
                        .toList();
    }
    
    public ExerciceDTO createExercice(ExerciceDTO exerciceDTO) {
        Exercice exercice = ExerciceMapper.INSTANCE.exerciceDTOToExercice(exerciceDTO);
        Exercice savedExercice = exerciceRepository.save(exercice);
        return ExerciceMapper.INSTANCE.exerciceToExerciceDTO(savedExercice);
    }
    
    public ExerciceDTO getExerciceById(Long id) {
        Exercice exercice = exerciceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Exercice not found"));
        return ExerciceMapper.INSTANCE.exerciceToExerciceDTO(exercice);
    }

    /* 

    * Au cas ou il faudra ajouter et supprimer des exercices 



    public ExerciceDTO updateExercice(Long id, ExerciceDTO exerciceDTO) {
        Exercice exercice = exerciceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Exercice not found"));
        
        // Mettez à jour les champs nécessaires
        exercice.setDescription(exerciceDTO.getDescription());
        exercice.setNom(exerciceDTO.getNom());
        exercice.setLienVideo(exerciceDTO.getLienVideo());
        exercice.setMateriel(exerciceDTO.getMateriel());
        exercice.setPartieCorps(exerciceDTO.getPartieCorps());
        exercice.setEquipementNecessaire(exerciceDTO.getEquipementNecessaire());
        exercice.setType(exerciceDTO.getType());
        exercice.setCaloriesBurned(exerciceDTO.getCaloriesBurned());
        exercice.setDistance(exerciceDTO.getDistance());
        
        Exercice updatedExercice = exerciceRepository.save(exercice);
        return ExerciceMapper.INSTANCE.exerciceToExerciceDTO(updatedExercice);
    }
    
    // Optionnel : Supprimer un exercice par ID
    public void deleteExercice(Long id) {
        exerciceRepository.deleteById(id);
    }

    */
} 