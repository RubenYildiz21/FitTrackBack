package com.fittrack.fit_track.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fittrack.fit_track.dto.LoginRequest;
import com.fittrack.fit_track.dto.LoginResponseDTO;
import com.fittrack.fit_track.dto.RegisterResponseDTO;
import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.mapper.UserMapper;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.UserRepository;
import com.fittrack.fit_track.utils.JwtUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserMapper userMapper; 

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        System.out.println("Login attempt for email: " + loginRequest.getEmail());
        if (bindingResult.hasErrors()) {
            System.out.println("Login request has validation errors.");
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        try {
            // Authentifier l'user via AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            System.out.println("Authentication successful for email: " + loginRequest.getEmail());
            // Définir le contexte de sécurité
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Générer le token JWT
            String token = jwtUtils.generateJwtToken(authentication);
            System.out.println("JWT Token generated: " + token);

            // Récupérer les détails de l'utilisateur authentifié
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Mapper l'utilisateur vers UserDTO
            UserDTO userDTO = userMapper.userToUserDTO(user);

            // Préparer la réponse avec DTO
            LoginResponseDTO loginResponse = new LoginResponseDTO();
            loginResponse.setToken(token);
            loginResponse.setUser(userDTO);

            System.out.println("Login response prepared for email: " + loginRequest.getEmail());
            return ResponseEntity.ok(loginResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("An unexpected error occurred");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
            @RequestParam("age") int age,
            @RequestParam("trainingLevel") String trainingLevel,
            @RequestParam("gender") String gender,
            @RequestParam("mainGoal") String mainGoal,
            @RequestParam("goalWeight") int goalWeight,
            @RequestParam("height") int height,
            @RequestParam("weight") int weight,
            @RequestParam("place") String place) {

        // Vérifier si l'email est déjà utilisé
        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        // Créer un nouvel utilisateur
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setAge(age);
        user.setTrainingLevel(trainingLevel);
        user.setGender(gender);
        user.setMainGoal(mainGoal);
        user.setGoalWeight(goalWeight);
        user.setHeight(height);
        user.setWeight(weight);
        user.setPlace(place);

        user.setRoles(Set.of("USER"));

        // Enregistrer l'image en tant que tableau de bytes
        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                user.setProfilePicture(Arrays.toString(profilePicture.getBytes()));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving profile picture");
            }
        }

        // Sauvegarder l'utilisateur après validation des informations
        User savedUser = userRepository.save(user);

        UserDTO userDTO = userMapper.userToUserDTO(savedUser);

        // Préparer la réponse avec DTO
        RegisterResponseDTO registerResponse = new RegisterResponseDTO();
        registerResponse.setUser(userDTO);

        return ResponseEntity.ok(registerResponse);
    }

    // Optionnel: Logout Endpoint
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Implémenter la logique de déconnexion si nécessaire
        return ResponseEntity.ok("Logged out successfully");
    }
}
