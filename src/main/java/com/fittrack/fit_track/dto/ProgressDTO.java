package com.fittrack.fit_track.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressDTO {

    @Getter
    @Setter
    private Map<String, Integer> repetitionsPerPeriod;

    @Getter
    @Setter
    private Map<String, Integer> setsPerPeriod;

    @Getter
    @Setter
    private Map<String, Double> weightLiftedPerPeriod;

    @Getter
    @Setter
    private Map<String, Double> caloriesBurnedPerPeriod;

    @Getter
    @Setter
    private Map<String, Double> distancePerPeriod;

    @Getter
    @Setter
    private Map<String, Double> personalRecords;

    @Getter
    @Setter
    private Map<String, Integer> workoutsPerDay;

    @Getter
    @Setter
    private double totalWeightLifted;

    @Getter
    @Setter
    private double maxWeightLifted;

    @Getter
    @Setter
    private String exerciseWithMaxWeight;
}
