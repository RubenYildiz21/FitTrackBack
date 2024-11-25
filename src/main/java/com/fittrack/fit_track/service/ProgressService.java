// src/main/java/com/fittrack/fit_track/service/ProgressService.java

package com.fittrack.fit_track.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fittrack.fit_track.dto.ProgressDTO;
import com.fittrack.fit_track.model.Bloc;
import com.fittrack.fit_track.model.Seance;
import com.fittrack.fit_track.model.Series;
import com.fittrack.fit_track.repository.SeanceRepository;

@Service
public class ProgressService {

    @Autowired
    private SeanceRepository seanceRepository;

    public ProgressDTO getUserProgress(Long userId, String period) {
        // Définir la période de temps
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate;

        switch (period.toLowerCase()) {
            case "monthly":
                startDate = endDate.minusMonths(1);
                break;
            case "yearly":
                startDate = endDate.minusYears(1);
                break;
            case "all":
                startDate = LocalDateTime.MIN; // Inclure toutes les données depuis le début
                break;
            case "weekly":
            default:
                startDate = endDate.minusWeeks(1);
                break;
        }

        List<Seance> seances = seanceRepository.findSeancesWithBlocsAndSeries(userId, startDate, endDate);

        Map<String, Integer> repsPerPeriod = new TreeMap<>();
        Map<String, Integer> setsPerPeriod = new TreeMap<>();
        Map<String, Double> weightLiftedPerPeriod = new TreeMap<>();
        Map<String, Double> caloriesPerPeriod = new TreeMap<>();
        Map<String, Double> distancePerPeriod = new TreeMap<>();
        Map<String, Double> personalRecords = new HashMap<>();
        Map<String, Integer> workoutsPerDay = new TreeMap<>();

        // Variables pour le poids maximal
        double totalWeightLifted = 0.0;
        double maxWeightLifted = 0.0;
        String exerciseWithMaxWeight = "";

        for (Seance seance : seances) {
            String periodKey = getPeriodKey(seance.getDateSeance(), period);
            String dayKey = seance.getDateSeance().toLocalDate().toString();

            // Track workouts per day
            workoutsPerDay.put(dayKey, workoutsPerDay.getOrDefault(dayKey, 0) + 1);

            for (Bloc bloc : seance.getBlocs()) {
                if (bloc.getSeries() != null && !bloc.getSeries().isEmpty()) {
                    // Increment sets per period
                    int numSets = bloc.getSeries().size();
                    setsPerPeriod.put(periodKey, setsPerPeriod.getOrDefault(periodKey, 0) + numSets);

                    for (Series series : bloc.getSeries()) {

                        // Get exercise name
                        String exerciseName = bloc.getExercice().getNom();

                        // Calculer le poids total soulevé
                        totalWeightLifted += series.getReps() * series.getPoids();

                        // Mettre à jour le poids maximal soulevé
                        if (series.getPoids() > maxWeightLifted) {
                            maxWeightLifted = series.getPoids();
                            exerciseWithMaxWeight = exerciseName;
                        }

                        // Calculate total weight lifted
                        double weightLifted = series.getReps() * series.getPoids();
                        weightLiftedPerPeriod.put(periodKey,
                                weightLiftedPerPeriod.getOrDefault(periodKey, 0.0) + weightLifted);

                        // Update personal records
                        double currentPR = personalRecords.getOrDefault(exerciseName, 0.0);
                        if (series.getPoids() > currentPR) {
                            personalRecords.put(exerciseName, series.getPoids());
                        }

                        // Calculate calories burned
                        Double calories = series.getCaloriesBurned() != null ? series.getCaloriesBurned() : 0.0;
                        caloriesPerPeriod.put(periodKey, caloriesPerPeriod.getOrDefault(periodKey, 0.0) + calories);

                        // Calculate distance
                        Double distance = series.getDistance() != null ? series.getDistance() : 0.0;
                        distancePerPeriod.put(periodKey, distancePerPeriod.getOrDefault(periodKey, 0.0) + distance);
                    }
                }
            }
        }

        ProgressDTO progress = new ProgressDTO();
        progress.setRepetitionsPerPeriod(repsPerPeriod);
        progress.setSetsPerPeriod(setsPerPeriod);
        progress.setWeightLiftedPerPeriod(weightLiftedPerPeriod);
        progress.setCaloriesBurnedPerPeriod(caloriesPerPeriod);
        progress.setDistancePerPeriod(distancePerPeriod);
        progress.setPersonalRecords(personalRecords);
        progress.setWorkoutsPerDay(workoutsPerDay);
        progress.setTotalWeightLifted(totalWeightLifted);
        progress.setMaxWeightLifted(maxWeightLifted);
        progress.setExerciseWithMaxWeight(exerciseWithMaxWeight);

        return progress;
    }

    private String getPeriodKey(LocalDateTime date, String period) {
        switch (period.toLowerCase()) {
            case "monthly":
                return date.toLocalDate().withDayOfMonth(1).toString(); // e.g., "2023-10-01"
            case "yearly":
                return String.valueOf(date.getYear()); // e.g., "2023"
            case "weekly":
            default:
                // Retourne le début de la semaine (Lundi)
                return date.with(DayOfWeek.MONDAY).toLocalDate().toString();
        }
    }
}
