// src/main/java/com/fittrack/fit_track/service/ProgressService.java

package com.fittrack.fit_track.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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
            case "weekly":
            default:
                startDate = endDate.minusWeeks(1);
                break;
        }

        List<Seance> seances = seanceRepository.findByUserIdAndDateSeanceBetween(userId, startDate, endDate);

        Map<String, Integer> repsPerPeriod = new TreeMap<>();
        Map<String, Double> caloriesPerPeriod = new TreeMap<>();
        Map<String, Double> distancePerPeriod = new TreeMap<>();

        for (Seance seance : seances) {
            String key = getPeriodKey(seance.getDateSeance(), period);

            for (Bloc bloc : seance.getBlocs()) {
                if (bloc.getSeries() != null) {
                    for (Series series : bloc.getSeries()) {
                        // Calculer les répétitions totales
                        repsPerPeriod.put(key, repsPerPeriod.getOrDefault(key, 0) + series.getReps());

                        // Calculer les calories brûlées
                        Double calories = series.getCaloriesBurned() != null ? series.getCaloriesBurned() : 0.0;
                        caloriesPerPeriod.put(key, caloriesPerPeriod.getOrDefault(key, 0.0) + calories);

                        // Calculer la distance parcourue
                        Double distance = series.getDistance() != null ? series.getDistance() : 0.0;
                        distancePerPeriod.put(key, distancePerPeriod.getOrDefault(key, 0.0) + distance);
                    }
                }
            }
        }

        ProgressDTO progress = new ProgressDTO();
        progress.setRepetitionsPerPeriod(repsPerPeriod);
        progress.setCaloriesBurnedPerPeriod(caloriesPerPeriod);
        progress.setDistancePerPeriod(distancePerPeriod);

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
                return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toLocalDate().toString();
        }
    }
}
