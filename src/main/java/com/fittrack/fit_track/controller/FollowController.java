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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/connection")
public class FollowController {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/follow")
    public ResponseEntity<Map<String, Object>> followUser(@RequestParam Long followerId, @RequestParam Long followId) {
        Map<String, Object> response = new HashMap<>();

        // Vérifier si l'utilisateur à suivre existe
        User userToFollow = userRepository.findById(followId).orElse(null);
        User currentUser = userRepository.findById(followerId).orElse(null);

        // Vérifier si les utilisateurs existent
        if (userToFollow == null || currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Check if the current user already follows the other user
        Optional<Follow> existingFollow = followRepository.findByFollowerAndFollow(currentUser, userToFollow);

        if (existingFollow.isPresent()) {
            // If they are already following, remove the follow relationship
            followRepository.delete(existingFollow.get());
            response.put("message", "Vous avez arrêté de suivre cet utilisateur.");
            response.put("action", "unfollow");
        } else {
            // Otherwise, create a new follow relationship
            Follow newFollow = new Follow();
            newFollow.setFollow(userToFollow);
            newFollow.setFollower(currentUser);

            Follow savedFollow = followRepository.save(newFollow);
            response.put("message", "Vous avez commencé à suivre cet utilisateur.");
            response.put("action", "follow");
            response.put("followData", savedFollow);
        }

        return ResponseEntity.ok(response);
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

    @GetMapping("/allFollow/{followerId}")
    public ResponseEntity<List<Long>> allFollow(@PathVariable Long followerId) {
        User follower = userRepository.findById(followerId).orElse(null);

        if (follower == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Retrieve the list of followed user IDs
        List<Long> followedUserIds = followRepository.findFollowedUserIds(followerId);

        return ResponseEntity.ok(followedUserIds);
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
