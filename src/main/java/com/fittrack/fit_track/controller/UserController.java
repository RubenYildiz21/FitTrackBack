package com.fittrack.fit_track.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // S'inscrire avec les informations personnelles
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

        // Enregistrer l'image en tant que tableau de bytes
        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                user.setProfilePicture(profilePicture.getBytes());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving profile picture");
            }
        }

        // Sauvegarder l'utilisateur après validation des informations
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // Récupérer les informations d'un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();
        return ResponseEntity.ok(user);
    }

    // Questions personnelles après inscription
    @PutMapping("/edit/user/{id}")
    public ResponseEntity<?> setPersonalInformations(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        // Récuperation de l'utilisateur 
        User user = userOpt.get();

        // Mettre à jour les données personnelles 
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setHeight(userDetails.getHeight());
        user.setWeight(userDetails.getWeight());

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/editGoals/user/{id}")
    public ResponseEntity<?> setPersonalObjectives(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        // Récuperation de l'utilisateur 
        User user = userOpt.get();

        // Mettre à jour les données personnelles 
        user.setMainGoal(userDetails.getMainGoal());
        user.setGoalWeight(userDetails.getGoalWeight());
        user.setHeight(userDetails.getHeight());
        user.setWeight(userDetails.getWeight());

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Récupérer la liste des utilisateurs
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // Récupérer la liste des utilisateurs apres une recherche
    @GetMapping("/users/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam("query") String searchTerm) {
        String firstName = searchTerm;
        String lastName = searchTerm;

        // Si le terme de recherche contient un espace, on suppose qu'il s'agit de "Prénom Nom"
        String[] parts = searchTerm.split(" ");
        if (parts.length >= 2) {
            firstName = parts[0];
            lastName = parts[1];
        }

        List<User> users = userRepository.findWithSearch(firstName, lastName, searchTerm);
        return ResponseEntity.ok(users);
    }
}