package com.fittrack.fit_track.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressDTO {

    private Map<String, Integer> repetitionsPerPeriod;

    private Map<String, Integer> setsPerPeriod;

    private Map<String, Double> weightLiftedPerPeriod;

    private Map<String, Double> caloriesBurnedPerPeriod;

    private Map<String, Double> distancePerPeriod;

    private Map<String, Double> personalRecords;

    private Map<String, Integer> workoutsPerDay;

    private double totalWeightLifted;

    private double maxWeightLifted;

    private String exerciseWithMaxWeight;
}
