package com.fittrack.fit_track.controller;

import com.fittrack.fit_track.model.Challenge;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.model.UserChallenge;
import com.fittrack.fit_track.repository.ChallengeRepository;
import com.fittrack.fit_track.repository.UserChallengeRepository;
import com.fittrack.fit_track.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private UserChallengeRepository userChallengeRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createChallenge(@RequestBody Challenge challenge) {
        if (challenge.getIdUser() == null || challenge.getTitle() == null || challenge.getExercise() == null ||
                challenge.getBeginingDate() == null || challenge.getEndingDate() == null || challenge.getDescription() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        Challenge savedChallenge = challengeRepository.save(challenge);

        UserChallenge userChallenge = new UserChallenge();
        userChallenge.setUserId(savedChallenge.getIdUser());
        userChallenge.setChallengeId(savedChallenge.getId());
        userChallenge.setUserScore(0L);

        UserChallenge savedUserChallenge = userChallengeRepository.save(userChallenge);

        Map<String, Object> response = new HashMap<>();
        response.put("challenge", savedChallenge);
        response.put("userChallenge", savedUserChallenge);

        return ResponseEntity.ok(response);
    }

    // Récupérer tous les défis
    @GetMapping("/all")
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, List<Challenge>>> getChallengesByUser(@PathVariable Long userId) {
        // Fetch challenges the user is participating in
        List<UserChallenge> userChallenges = userChallengeRepository.findByUserId(userId);
        List<Long> participatingChallengeIds = userChallenges.stream()
                .map(UserChallenge::getChallengeId)
                .collect(Collectors.toList());

        List<Challenge> participatingChallenges = challengeRepository.findAllById(participatingChallengeIds);

        // Fetch joinable challenges
        List<Challenge> allChallenges = challengeRepository.findAll();
        List<Challenge> joinableChallenges = allChallenges.stream()
                .filter(challenge -> !participatingChallengeIds.contains(challenge.getId()))
                .collect(Collectors.toList());

        // Prepare response
        Map<String, List<Challenge>> response = new HashMap<>();
        response.put("participating", participatingChallenges);
        response.put("joinable", joinableChallenges);

        return ResponseEntity.ok(response);
    }

    // Récupérer un défi par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getChallengeById(@PathVariable Long id) {
        Optional<Challenge> challengeOpt = challengeRepository.findById(id);

        if (challengeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Challenge not found");
        }

        return ResponseEntity.ok(challengeOpt.get());
    }

    // Modifier un défi
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge) {
        Optional<Challenge> challengeOpt = challengeRepository.findById(id);

        if (challengeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Challenge not found");
        }

        Challenge existingChallenge = challengeOpt.get();
        existingChallenge.setTitle(updatedChallenge.getTitle());
        existingChallenge.setExercise(updatedChallenge.getExercise());
        existingChallenge.setBeginingDate(updatedChallenge.getBeginingDate());
        existingChallenge.setEndingDate(updatedChallenge.getEndingDate());

        Challenge savedChallenge = challengeRepository.save(existingChallenge);
        return ResponseEntity.ok(savedChallenge);
    }

    // Supprimer un défi
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteChallenge(@PathVariable Long id) {
        Optional<Challenge> challengeOpt = challengeRepository.findById(id);

        if (challengeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Challenge not found");
        }

        challengeRepository.deleteById(id);
        return ResponseEntity.ok("Challenge deleted successfully");
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinChallenge(@RequestBody UserChallenge userChallenge) {

        if (userChallenge.getUserId() == null || userChallenge.getChallengeId() == null) {
            return ResponseEntity.badRequest().body("User ID and Challenge ID are required");
        }
        userChallenge.setUserScore(0L);
        UserChallenge savedUserChallenge = userChallengeRepository.save(userChallenge);
        return ResponseEntity.ok(savedUserChallenge);
    }


    @GetMapping("/leaderBoard/{challengeId}")
    public ResponseEntity<List<Map<String, Object>>> getLeaderBoard(@PathVariable Long challengeId) {
        // Fetch users participating in the challenge
        List<UserChallenge> participatingUsers = userChallengeRepository.findByChallengeId(challengeId);

        if (participatingUsers.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>()); // Return an empty list if no participants
        }

        // Map each user to their score
        List<Map<String, Object>> leaderboard = participatingUsers.stream().map(userChallenge -> {
                    Optional<User> user = userRepository.findById(userChallenge.getUserId());

                    if (user.isPresent()) {
                        Map<String, Object> entry = new HashMap<>();
                        entry.put("name", user.get().getFirstName() + " " + user.get().getLastName());
                        entry.put("score", userChallenge.getUserScore()); // Add user score
                        return entry;
                    }

                    return null; // Handle cases where the user might not exist (shouldn't happen normally)
                }).filter(Objects::nonNull) // Remove null entries
                .sorted((a, b) -> Long.compare((Long) b.get("score"), (Long) a.get("score"))) // Sort by score descending
                .collect(Collectors.toList());

        return ResponseEntity.ok(leaderboard);
    }

    @PostMapping("/quit")
    public ResponseEntity<?> quitChallenge(@RequestBody UserChallenge userChallenge) {

        if (userChallenge.getUserId() == null || userChallenge.getChallengeId() == null) {
            return ResponseEntity.badRequest().body("User ID and Challenge ID are required");
        }

        userChallengeRepository.delete(userChallenge);
        return ResponseEntity.ok(userChallenge);}

}
