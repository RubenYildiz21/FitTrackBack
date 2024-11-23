// src/main/java/com/fittrack/fit_track/dto/UserDTO.java
package com.fittrack.fit_track.dto;

import java.util.Set;

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
    private Set<String> roles;
    private String profilePicture;

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long idUser) {
        this.id = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }

    public String getTrainingLevel() {
        return trainingLevel;
    }
    
    public void setTrainingLevel(String trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMainGoal() {
        return mainGoal;
    }
    
    public void setMainGoal(String mainGoal) {
        this.mainGoal = mainGoal;
    }

    public int getGoalWeight() {
        return goalWeight;
    }
    
    public void setGoalWeight(int goalWeight) {
        this.goalWeight = goalWeight;
    }

    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPlace() {
        return place;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }

    public Set<String> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
    
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
