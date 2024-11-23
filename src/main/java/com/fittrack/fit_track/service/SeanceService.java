// src/main/java/com/fittrack/fit_track/service/SeanceService.java

package com.fittrack.fit_track.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fittrack.fit_track.dto.ExerciceDTO;
import com.fittrack.fit_track.dto.SeanceDTO;
import com.fittrack.fit_track.mapper.SeanceMapper;
import com.fittrack.fit_track.model.Bloc;
import com.fittrack.fit_track.model.Exercice;
import com.fittrack.fit_track.model.Seance;
import com.fittrack.fit_track.model.Series;
import com.fittrack.fit_track.model.enums.Equipement;
import com.fittrack.fit_track.model.enums.ExerciseType;
import com.fittrack.fit_track.model.enums.PartieCorps;
import com.fittrack.fit_track.repository.ExerciceRepository;
import com.fittrack.fit_track.repository.SeanceRepository;

@Service
@Validated
@Transactional
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private ExerciceRepository exerciceRepository;

    // Créer une séance à partir d'un DTO et retourner le DTO créé
    public SeanceDTO enregistrerSeance(SeanceDTO seanceDTO) {
        // Mapper le DTO vers l'entité
        Seance seance = SeanceMapper.INSTANCE.seanceDTOToSeance(seanceDTO);

        // Valider la séance et ses blocs
        if (seance.getDateSeance() == null) {
            throw new IllegalArgumentException("La date de la séance est obligatoire");
        }
        
        if (seance.getBlocs() == null || seance.getBlocs().isEmpty()) {
            throw new IllegalArgumentException("Une séance doit contenir au moins un bloc d'exercice");
        }

        // Validation et calcul des stats pour chaque bloc
        for (Bloc bloc : seance.getBlocs()) {
            validerBloc(bloc);
            computeStats(bloc);
        }

        // Sauvegarder la séance
        Seance nouvelleSeance = seanceRepository.save(seance);

        // Mapper l'entité sauvegardée vers le DTO
        return SeanceMapper.INSTANCE.seanceToSeanceDTO(nouvelleSeance);
    }

    // Valider un bloc
    private void validerBloc(Bloc bloc) {
        if(bloc.getExercice() == null) {
            throw new IllegalArgumentException("Un bloc doit contenir un exercice");
        }
        
        // Vérifier que l'exercice existe
        Exercice exercice = exerciceRepository.findById(bloc.getExercice().getIdExercice())
            .orElseThrow(() -> new IllegalArgumentException("L'exercice spécifié n'existe pas"));
        
        bloc.setExercice(exercice);

        // Validation et calcul des series
        if(bloc.getSeries() == null || bloc.getSeries().isEmpty()) {
            throw new IllegalArgumentException("Un bloc doit contenir au moins une série");
        }

        for(Series series : bloc.getSeries()) {
            validerSerie(series);
            computeStats(series, bloc);
        }
    }

    // Valider le format des temps
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


    private void validerSerie(Series series) {
        if (series.getReps() == null || series.getReps() <= 0) {
            throw new IllegalArgumentException("Le nombre de répétitions doit être supérieur à 0");
        }
        if (series.getSerie() == null || series.getSerie() <= 0) {
            throw new IllegalArgumentException("Le numéro de série doit être supérieur à 0");
        }
        if (series.getPoids() == null || series.getPoids() < 0) {
            throw new IllegalArgumentException("Le poids ne peut pas être négatif");
        }
        // Validation du format des temps
        validateTimeFormat(series.getTempsRepos(), "temps de repos");
        validateTimeFormat(series.getTempsDeRepetition(), "temps de répétition");
    }

    private void computeStats(Bloc bloc){
        double totalCaloriesBurned = 0.0;
        double totalDistance = 0.0;

        for (Series series : bloc.getSeries()) {
            computeStats(series, bloc);
            totalCaloriesBurned += series.getCaloriesBurned();
            totalDistance += series.getDistance();
        }

        bloc.setCaloriesBurned(totalCaloriesBurned);
        bloc.setDistance(totalDistance);
    }
    // Calculer les statistiques pour un bloc
    private void computeStats(Series series, Bloc bloc){
        Exercice exercice = bloc.getExercice();
        ExerciseType type = exercice.getType();

        if (type == null) {
            throw new IllegalArgumentException("Exercise type is null for exercise ID " + exercice.getIdExercice());
        }

        double weight = series.getPoids();

        double durationHours = calculateDuration(series); // Calculer la durée en heures

        double caloriesBurned = 0.0;
        double distance = 0.0;

        switch(type) {
            case STRENGTH -> {
                // Exemple de formule : MET pour entraînement de force ~6
                double metStrength = 6.0;
                caloriesBurned = metStrength * weight * durationHours;
            }
            case CARDIO -> {
                // Exemple de formule : MET pour course ~9.8, vélo ~7.5
                double metCardio;
                // Vous pouvez affiner en fonction du type spécifique de cardio
                metCardio = 7.5; // Supposons du vélo
                caloriesBurned = metCardio * weight * durationHours;

                // Calculer la distance basée sur la durée et la vitesse moyenne
                double avgSpeed; // en km/h
                if (exercice.getPartieCorps() == PartieCorps.JAMBES) { // Exemple
                    avgSpeed = 20.0; // Vélocité moyenne pour le vélo
                } else {
                    avgSpeed = 10.0; // Vélocité moyenne pour la course
                }
                distance = avgSpeed * durationHours;
            }
            default -> {
                
                // Autres types d'exercices peuvent être traités ici
            }
        }
        // Autres types d'exercices peuvent être traités ici

        bloc.setCaloriesBurned(caloriesBurned);
        bloc.setDistance(distance);
    }

    // Calculer la durée totale en heures basée sur les séries, temps de répétition et temps de repos
    private double calculateDuration(Series series) {
        int serie = series.getSerie();
        String tempsDeRepetition = series.getTempsDeRepetition(); // "HH:mm:ss"
        String tempsRepos = series.getTempsRepos(); // "HH:mm:ss"

        // Convertir les temps en secondes
        long repetitionSeconds = parseTimeToSeconds(tempsDeRepetition);
        long reposSeconds = parseTimeToSeconds(tempsRepos);

        // Durée totale : série * (temps de répétition + temps de repos)
        long totalSeconds = serie * (repetitionSeconds + reposSeconds);
        double hours = totalSeconds / 3600.0;
        return hours;
    }

    // Convertir un temps au format "HH:mm:ss" en secondes
    private long parseTimeToSeconds(String time) {
        if (time == null || time.isEmpty()) return 0;
        String[] parts = time.split(":");
        if (parts.length != 3) return 0;
        try {
            long hours = Long.parseLong(parts[0]);
            long minutes = Long.parseLong(parts[1]);
            long seconds = Long.parseLong(parts[2]);
            return hours * 3600 + minutes * 60 + seconds;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Filtrer les exercices par partie du corps et retourner les DTOs
    public List<ExerciceDTO> filtrerExercices(PartieCorps partieCorps) {
        List<Exercice> exercices = exerciceRepository.findByPartieCorps(partieCorps);
        return exercices.stream()
                        .map(com.fittrack.fit_track.mapper.ExerciceMapper.INSTANCE::exerciceToExerciceDTO)
                        .toList();
    }

    // Filtrer les exercices par équipement et retourner les DTOs
    public List<ExerciceDTO> filtrerExercices(Equipement equipement) {
        List<Exercice> exercices = exerciceRepository.findByEquipementNecessaire(equipement);
        return exercices.stream()
                        .map(com.fittrack.fit_track.mapper.ExerciceMapper.INSTANCE::exerciceToExerciceDTO)
                        .toList();
    }

    // Récupérer une séance par ID et la convertir en DTO
    public Optional<SeanceDTO> getSeance(Long id) {
        Optional<Seance> seanceOpt = seanceRepository.findById(id);
        return seanceOpt.map(SeanceMapper.INSTANCE::seanceToSeanceDTO);
    }
}
