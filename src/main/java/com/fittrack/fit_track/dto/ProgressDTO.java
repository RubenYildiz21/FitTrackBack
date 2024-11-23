package com.fittrack.fit_track.dto;

import java.util.Map;

import lombok.Data;

@Data
public class ProgressDTO {
    private Map<String, Integer> repetitionsPerPeriod; 
    private Map<String, Double> caloriesBurnedPerPeriod;
    private Map<String, Double> distancePerPeriod;

}
