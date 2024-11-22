package com.fittrack.fit_track.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.mapper.UserMapper;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;
    // Récupérer les informations d'un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        UserDTO userDTO = userMapper.userToUserDTO(userOpt.get());
        return ResponseEntity.ok(userDTO);
    }

    // Questions personnelles après inscription
    @PutMapping("/{id}")
    public ResponseEntity<?> setPersonalInformations(@PathVariable Long id, @RequestBody UserDTO userDetailsDTO) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Récuperation de l'utilisateur 
        User user = userOpt.get();

        // Mettre à jour les données personnelles 
        user.setFirstName(userDetailsDTO.getFirstName());
        user.setLastName(userDetailsDTO.getLastName());
        user.setHeight(userDetailsDTO.getHeight());
        user.setWeight(userDetailsDTO.getWeight());

        User updatedUser = userRepository.save(user);
        UserDTO updatedUserDTO = userMapper.userToUserDTO(updatedUser);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @PutMapping("/{id}/goals")
    public ResponseEntity<?> setPersonalObjectives(@PathVariable Long id, @RequestBody UserDTO userDetailsDTO) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Récuperation de l'utilisateur 
        User user = userOpt.get();

        // Mettre à jour les données personnelles 
        user.setMainGoal(userDetailsDTO.getMainGoal());
        user.setGoalWeight(userDetailsDTO.getGoalWeight());
        user.setHeight(userDetailsDTO.getHeight());
        user.setWeight(userDetailsDTO.getWeight());

        User updatedUser = userRepository.save(user);
        UserDTO updatedUserDTO = userMapper.userToUserDTO(updatedUser);
        return ResponseEntity.ok(updatedUserDTO);
    }

    // Récupérer la liste des utilisateurs
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream()
                                      .map(userMapper::userToUserDTO)
                                      .toList();
        return ResponseEntity.ok(userDTOs);
    }

    // Récupérer la liste des utilisateurs apres une recherche
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam("query") String searchTerm) {
        String firstName = searchTerm;
        String lastName = searchTerm;

        // Si le terme de recherche contient un espace, on suppose qu'il s'agit de "Prénom Nom"
        String[] parts = searchTerm.split(" ");
        if (parts.length >= 2) {
            firstName = parts[0];
            lastName = parts[1];
        }

        List<User> users = userRepository.findWithSearch(firstName, lastName, searchTerm);
        List<UserDTO> userDTOs = users.stream()
                                      .map(userMapper::userToUserDTO)
                                      .toList();
        return ResponseEntity.ok(userDTOs);
    }
}
