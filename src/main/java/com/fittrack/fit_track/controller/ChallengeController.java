package com.fittrack.fit_track.controller;

import com.fittrack.fit_track.dto.ChallengeDTO;
import com.fittrack.fit_track.dto.UserChallengeDTO;
import com.fittrack.fit_track.mapper.ChallengeMapper;
import com.fittrack.fit_track.mapper.UserChallengeMapper;
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

    @Autowired
    private ChallengeMapper challengeMapper;

    @Autowired
    private UserChallengeMapper userChallengeMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createChallenge(@RequestBody Challenge challenge) {
        if (challenge.getIdUser() == null || challenge.getTitle() == null || challenge.getExercise() == null ||
                challenge.getBeginingDate() == null || challenge.getEndingDate() == null || challenge.getDescription() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        // Save challenge and convert to DTO
        ChallengeDTO savedChallenge = challengeMapper.challengeToDTO(challengeRepository.save(challenge));

        // Create user challenge
        UserChallenge userChallenge = new UserChallenge();
        userChallenge.setUserId(savedChallenge.getId_user());
        userChallenge.setChallengeId(savedChallenge.getId());
        userChallenge.setUserScore(0L);

        // Save user challenge and convert to DTO
        UserChallengeDTO savedUserChallenge = userChallengeMapper.userChallengeToDTO(userChallengeRepository.save(userChallenge));

        Map<String, Object> response = new HashMap<>();
        response.put("challenge", savedChallenge);
        response.put("userChallenge", savedUserChallenge);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChallengeDTO>> getAllChallenges() {
        List<ChallengeDTO> challenges = challengeRepository.findAll()
                .stream()
                .map(challengeMapper::challengeToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, List<ChallengeDTO>>> getChallengesByUser(@PathVariable Long userId) {
        // Fetch challenges the user is participating in
        List<UserChallenge> userChallenges = userChallengeRepository.findByUserId(userId);

        List<Long> participatingChallengeIds = userChallenges.stream()
                .map(UserChallenge::getChallengeId)
                .collect(Collectors.toList());

        // Convert challenges to DTO
        List<ChallengeDTO> participatingChallenges = challengeRepository.findAllById(participatingChallengeIds)
                .stream()
                .map(challengeMapper::challengeToDTO)
                .collect(Collectors.toList());

        // Fetch and map joinable challenges
        List<ChallengeDTO> joinableChallenges = challengeRepository.findAll()
                .stream()
                .filter(challenge -> !participatingChallengeIds.contains(challenge.getId()))
                .map(challengeMapper::challengeToDTO)
                .collect(Collectors.toList());

        Map<String, List<ChallengeDTO>> response = new HashMap<>();
        response.put("participating", participatingChallenges);
        response.put("joinable", joinableChallenges);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChallengeById(@PathVariable Long id) {
        return challengeRepository.findById(id)
                .<ResponseEntity<?>>map(challenge -> ResponseEntity.ok(challengeMapper.challengeToDTO(challenge))) // Explicit ResponseEntity type
                .orElseGet(() -> ResponseEntity.badRequest().body("Challenge not found"));
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge) {
        return challengeRepository.findById(id)
                .<ResponseEntity<?>>map(existingChallenge -> {
                    // Update fields
                    existingChallenge.setTitle(updatedChallenge.getTitle());
                    existingChallenge.setExercise(updatedChallenge.getExercise());
                    existingChallenge.setBeginingDate(updatedChallenge.getBeginingDate());
                    existingChallenge.setEndingDate(updatedChallenge.getEndingDate());

                    ChallengeDTO updatedChallengeDTO = challengeMapper.challengeToDTO(challengeRepository.save(existingChallenge));
                    return ResponseEntity.ok(updatedChallengeDTO);
                })
                .orElseGet(() -> ResponseEntity.badRequest().body("Challenge not found"));
    }


    @PostMapping("/join")
    public ResponseEntity<?> joinChallenge(@RequestBody UserChallenge userChallenge) {
        if (userChallenge.getUserId() == null || userChallenge.getChallengeId() == null) {
            return ResponseEntity.badRequest().body("User ID and Challenge ID are required");
        }

        // Validate challenge and user existence
        if (!challengeRepository.existsById(userChallenge.getChallengeId()) || !userRepository.existsById(userChallenge.getUserId())) {
            return ResponseEntity.badRequest().body("Invalid Challenge ID or User ID");
        }

        userChallenge.setUserScore(0L);
        UserChallengeDTO savedUserChallenge = userChallengeMapper.userChallengeToDTO(userChallengeRepository.save(userChallenge));
        return ResponseEntity.ok(savedUserChallenge);
    }

    @PostMapping("/quit")
    public ResponseEntity<?> quitChallenge(@RequestBody UserChallenge userChallenge) {
        if (userChallenge.getUserId() == null || userChallenge.getChallengeId() == null) {
            return ResponseEntity.badRequest().body("User ID and Challenge ID are required");
        }

        Optional<UserChallenge> existingUserChallenge = userChallengeRepository.findByUserIdAndChallengeId(
                userChallenge.getUserId(),
                userChallenge.getChallengeId()
        );

        if (existingUserChallenge.isEmpty()) {
            return ResponseEntity.badRequest().body("User is not part of this challenge");
        }

        userChallengeRepository.delete(existingUserChallenge.get());
        return ResponseEntity.ok("Successfully left the challenge");
    }
}
