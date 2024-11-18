package com.fittrack.fit_track.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


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
    @PutMapping("/{id}")
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

    @PutMapping("/{id}/goals")
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
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // Récupérer la liste des utilisateurs apres une recherche
    @GetMapping("/search")
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
