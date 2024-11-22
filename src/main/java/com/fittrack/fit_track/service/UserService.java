// src/main/java/com/fittrack/fit_track/service/UserService.java

package com.fittrack.fit_track.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.mapper.UserMapper;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    // Récupérer un utilisateur par son ID et le convertir en DTO
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                             .map(userMapper::userToUserDTO);
    }

    // Récupérer un utilisateur par son email et le convertir en DTO
    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                             .map(userMapper::userToUserDTO);
    }

    // Récupérer tous les utilisateurs et les convertir en DTOs
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(userMapper::userToUserDTO)
                    .collect(Collectors.toList());
    }

    // Rechercher des utilisateurs par terme de recherche et les convertir en DTOs
    public List<UserDTO> searchUsers(String firstName, String lastName, String query) {
        List<User> users = userRepository.findWithSearch(firstName, lastName, query);
        return users.stream()
                    .map(userMapper::userToUserDTO)
                    .collect(Collectors.toList());
    }

    // Mettre à jour les informations personnelles d'un utilisateur et retourner le DTO mis à jour
    public UserDTO updatePersonalInformations(Long id, UserDTO userDetailsDTO) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Mettre à jour les champs personnels
        user.setFirstName(userDetailsDTO.getFirstName());
        user.setLastName(userDetailsDTO.getLastName());
        user.setHeight(userDetailsDTO.getHeight());
        user.setWeight(userDetailsDTO.getWeight());

        User updatedUser = userRepository.save(user);
        return userMapper.userToUserDTO(updatedUser);
    }

    // Mettre à jour les objectifs personnels d'un utilisateur et retourner le DTO mis à jour
    public UserDTO updatePersonalObjectives(Long id, UserDTO userDetailsDTO) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Mettre à jour les objectifs personnels
        user.setMainGoal(userDetailsDTO.getMainGoal());
        user.setGoalWeight(userDetailsDTO.getGoalWeight());
        user.setHeight(userDetailsDTO.getHeight());
        user.setWeight(userDetailsDTO.getWeight());

        User updatedUser = userRepository.save(user);
        return userMapper.userToUserDTO(updatedUser);
    }
}
