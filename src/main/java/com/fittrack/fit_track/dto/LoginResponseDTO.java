// src/main/java/com/fittrack/fit_track/dto/LoginResponseDTO.java
package com.fittrack.fit_track.dto;

public class LoginResponseDTO {
    private String token;
    private UserDTO user;

    // Getters et Setters

    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }
    
    public void setUser(UserDTO user) {
        this.user = user;
    }
}
