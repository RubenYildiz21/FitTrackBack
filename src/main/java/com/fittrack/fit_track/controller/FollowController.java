package com.fittrack.fit_track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fittrack.fit_track.model.Follow;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.FollowRepository;
import com.fittrack.fit_track.repository.UserRepository;

@RestController
@RequestMapping("/api/connection")
public class FollowController {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/follow")
    public ResponseEntity<?> followUser(@RequestParam Long followerId, @RequestParam Long followId) {
        // Vérifier si l'utilisateur à suivre existe
        User userToFollow = userRepository.findById(followId).orElse(null);
        User currentUser = userRepository.findById(followerId).orElse(null);

        // Vérifier si les utilisateurs existent
        if (userToFollow == null || currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur introuvable.");
        }

        // Vérifier si ils se follow déjà
        boolean alreadyFollowing = followRepository.existsByFollowerAndFollow(currentUser, userToFollow);
        if (alreadyFollowing) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vous suivez déjà cet utilisateur.");
        }

        // Créer une nouvelle connexion
        Follow follow = new Follow(); // Créez une nouvelle instance de Follow
        follow.setFollow(userToFollow);
        follow.setFollower(currentUser);

        // Sauvegarder la connexion dans la base de données
        Follow savedConnection = followRepository.save(follow);

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
        boolean isFollowing = followRepository.findByFollowerAndFollow(follower, follow).isPresent();

        return ResponseEntity.ok(isFollowing);
    }

    // Méthode pour compter le nombre de followers d'un utilisateur
    @GetMapping("/followersCount/{userId}")
    public ResponseEntity<Long> getFollowersCount(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        long count = followRepository.countByFollow(user);
        return ResponseEntity.ok(count);
    }

    // Méthode pour compter le nombre d'utilisateurs suivis par un utilisateur
    @GetMapping("/followingCount/{userId}")
    public ResponseEntity<Long> getFollowingCount(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        long count = followRepository.countByFollower(user);
        return ResponseEntity.ok(count);
    }
}
