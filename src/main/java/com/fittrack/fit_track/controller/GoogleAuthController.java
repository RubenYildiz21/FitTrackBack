package com.fittrack.fit_track.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class GoogleAuthController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/google")
    public ResponseEntity<?> authenticateGoogle(@RequestBody GoogleTokenRequest request) {
        try {
            if (request.getToken() == null) {
                return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Token is required"
                ));
            }
            
            // TODO: Verify the token with Google's API
            // For now, just returning success
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Authentication successful"
            ));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "message", e.getMessage()
            ));
        }
    }
}

class GoogleTokenRequest {
    private String token;
    private String clientId;

    // Getters and setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
}
