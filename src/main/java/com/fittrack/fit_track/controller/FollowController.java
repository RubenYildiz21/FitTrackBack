package com.fittrack.fit_track.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.dto.FollowDTO;
import com.fittrack.fit_track.mapper.FollowMapper;
import com.fittrack.fit_track.model.Follow;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.FollowRepository;
import com.fittrack.fit_track.repository.UserRepository;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, Object>> followUser(@RequestParam Long followerId, @RequestParam Long followId) {
        Map<String, Object> response = new HashMap<>();

        // Vérifier si l'utilisateur à suivre existe
        User userToFollow = userRepository.findById(followId).orElse(null);
        User currentUser = userRepository.findById(followerId).orElse(null);

        // Vérifier si les utilisateurs existent
        if (userToFollow == null || currentUser == null) {
            response.put("message", "Utilisateur non trouvé");
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
            FollowDTO followDTO = FollowMapper.INSTANCE.followToFollowDTO(savedFollow);
            response.put("message", "Vous avez commencé à suivre cet utilisateur.");
            response.put("action", "follow");
            response.put("followData", followDTO);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/isFollowing/{followerId}/{followId}")
    @PreAuthorize("isAuthenticated()")
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

    @GetMapping("/{followerId}/following")
    @PreAuthorize("isAuthenticated()")
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
    @GetMapping("/{userId}/followers/count")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Long> getFollowersCount(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        long count = followRepository.countByFollow(user);
        System.out.println("follower count : " + count);
        return ResponseEntity.ok(count);
    }

    // Méthode pour compter le nombre d'utilisateurs suivis par un utilisateur
    @GetMapping("/{userId}/following/count")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Long> getFollowingCount(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        long count = followRepository.countByFollower(user);
        return ResponseEntity.ok(count);
    }
}
