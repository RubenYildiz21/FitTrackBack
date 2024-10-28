package com.fittrack.fit_track.controller;

import com.fittrack.fit_track.model.Connection;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.ConnectionRepository;
import com.fittrack.fit_track.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/connection")
public class ConnectionController {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private UserRepository userRepository;

    // Follow un user
    @PostMapping("/follow")
    public ResponseEntity<?> followUser(@Validated @RequestBody Connection connection) {
        // Vérifier si l'utilisateur à suivre existe
        User userToFollow = userRepository.findById(connection.getFollow().getIdUser()).orElse(null);
        User currentUser = userRepository.findById(connection.getFollower().getIdUser()).orElse(null);

        // Créer une nouvelle connexion
        connection.setFollow(userToFollow);
        connection.setFollower(currentUser);

        // Sauvegarder la connexion dans la base de données
        Connection savedConnection = connectionRepository.save(connection);

        return ResponseEntity.ok(savedConnection);
    }

    @GetMapping("/isFollowing/{followerId}/{followId}")
    public ResponseEntity<Boolean> isFollowing(@PathVariable Long followerId, @PathVariable Long followId) {
        User follower = userRepository.findById(followerId).orElse(null);
        User follow = userRepository.findById(followId).orElse(null);

        if (follower == null || follow == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Vérifier si la connexion existe
        boolean isFollowing = connectionRepository.findByFollowerAndFollow(follower, follow).isPresent();

        return ResponseEntity.ok(isFollowing);
    }

    // Méthode pour compter le nombre de followers d'un utilisateur
    @GetMapping("/followersCount/{userId}")
    public ResponseEntity<Long> getFollowersCount(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        long count = connectionRepository.countByFollow(user);
        return ResponseEntity.ok(count);
    }

    // Méthode pour compter le nombre d'utilisateurs suivis par un utilisateur
    @GetMapping("/followingCount/{userId}")
    public ResponseEntity<Long> getFollowingCount(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        long count = connectionRepository.countByFollower(user);
        return ResponseEntity.ok(count);
    }
}
