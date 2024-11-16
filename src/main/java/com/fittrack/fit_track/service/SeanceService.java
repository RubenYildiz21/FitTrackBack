package com.fittrack.fit_track.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fittrack.fit_track.model.Bloc;
import com.fittrack.fit_track.model.Exercice;
import com.fittrack.fit_track.model.Seance;
import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.PartieCorps;
import com.fittrack.fit_track.repository.ExerciceRepository;
import com.fittrack.fit_track.repository.SeanceRepository;

import jakarta.validation.Valid;

@Service
@Validated
@Transactional
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private ExerciceRepository exerciceRepository;

    public Seance enregistrerSeance(@Valid Seance seance) {
        if (seance.getDateSeance() == null) {
            throw new IllegalArgumentException("La date de la séance est obligatoire");
        }
        
        if (seance.getBlocs() == null || seance.getBlocs().isEmpty()) {
            throw new IllegalArgumentException("Une séance doit contenir au moins un bloc d'exercice");
        }

        // Validation des blocs
        for (Bloc bloc : seance.getBlocs()) {
            validerBloc(bloc);
        }

        return seanceRepository.save(seance);
    }

    private void validerBloc(Bloc bloc) {
        if (bloc.getRepetition() <= 0) {
            throw new IllegalArgumentException("Le nombre de répétitions doit être supérieur à 0");
        }
        if (bloc.getSerie() <= 0) {
            throw new IllegalArgumentException("Le nombre de séries doit être supérieur à 0");
        }
        if (bloc.getPoids() < 0) {
            throw new IllegalArgumentException("Le poids ne peut pas être négatif");
        }
        if (bloc.getExercice() == null) {
            throw new IllegalArgumentException("Un exercice est requis pour chaque bloc");
        }
        
        // Vérifier que l'exercice existe
        exerciceRepository.findById(bloc.getExercice().getIdExercice())
            .orElseThrow(() -> new IllegalArgumentException("L'exercice spécifié n'existe pas"));
        
        // Validation du format des temps
        validateTimeFormat(bloc.getTempsRepos(), "temps de repos");
        validateTimeFormat(bloc.getTempsDeRepetition(), "temps de répétition");
    }

    private void validateTimeFormat(String time, String fieldName) {
        if (time != null) {
            try {
                // Vérifier si le format est valide (HH:mm:ss)
                LocalTime.parse(time);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                    String.format("Format invalide pour le %s. Utilisez le format HH:mm:ss", fieldName)
                );
            }
        }
    }


    public List<Exercice> filtrerExercices(PartieCorps partieCorps) {
        return exerciceRepository.findByPartieCorps(partieCorps);
    }

    public List<Exercice> filtrerExercices(Equipement equipement) {
        return exerciceRepository.findByEquipementNecessaire(equipement);
    }

    public Optional<Seance> getSeance(Long id) {
        return seanceRepository.findById(id);
    }
} 