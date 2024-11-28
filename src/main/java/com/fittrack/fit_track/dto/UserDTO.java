// src/main/java/com/fittrack/fit_track/dto/UserDTO.java
package com.fittrack.fit_track.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    private String trainingLevel;

    @Getter
    @Setter
    private String gender;

    @Getter
    @Setter
    private String mainGoal;

    @Getter
    @Setter
    private int goalWeight;

    @Getter
    @Setter
    private int height;

    @Getter
    @Setter
    private int weight;

    @Getter
    @Setter
    private String place;

    @Getter
    @Setter
    private String profilePicture;

    @Getter
    @Setter
    private Set<String> roles;
}
