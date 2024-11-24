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
    private Map<String, Double> caloriesBurnedPerPeriod;
    private Map<String, Double> distancePerPeriod;

}
