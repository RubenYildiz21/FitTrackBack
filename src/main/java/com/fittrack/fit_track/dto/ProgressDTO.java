package com.fittrack.fit_track.dto;

import java.util.Map;

public class ProgressDTO {
    private Map<String, Integer> repetitionsPerPeriod; 
    private Map<String, Double> caloriesBurnedPerPeriod;
    private Map<String, Double> distancePerPeriod;
    
    // Getters and Setters

    public Map<String, Integer> getRepetitionsPerPeriod() {
        return repetitionsPerPeriod;
    }

    public void setRepetitionsPerPeriod(Map<String, Integer> repetitionsPerPeriod) {
        this.repetitionsPerPeriod = repetitionsPerPeriod;
    }

    public Map<String, Double> getCaloriesBurnedPerPeriod() {
        return caloriesBurnedPerPeriod;
    }

    public void setCaloriesBurnedPerPeriod(Map<String, Double> caloriesBurnedPerPeriod) {
        this.caloriesBurnedPerPeriod = caloriesBurnedPerPeriod;
    }

    public Map<String, Double> getDistancePerPeriod() {
        return distancePerPeriod;
    }

    public void setDistancePerPeriod(Map<String, Double> distancePerPeriod) {
        this.distancePerPeriod = distancePerPeriod;
    }
}
