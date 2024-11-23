// src/main/java/com/fittrack/fit_track/dto/UserDTO.java
package com.fittrack.fit_track.dto;

import java.util.Set;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String trainingLevel;
    private String gender;
    private String mainGoal;
    private int goalWeight;
    private int height;
    private int weight;
    private String place;
    private String profilePicture;
    private Set<String> roles;
}
