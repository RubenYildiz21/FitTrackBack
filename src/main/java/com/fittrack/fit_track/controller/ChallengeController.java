package com.fittrack.fit_track.controller;

import com.fittrack.fit_track.model.Challenge;
import com.fittrack.fit_track.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeRepository challengeRepository;

    // Créer un nouveau défi
    @PostMapping("/create")
    public ResponseEntity<?> createChallenge(@RequestBody Challenge challenge) {
        if (challenge.getIdUser() == null || challenge.getTitle() == null || challenge.getExercise() == null ||
                challenge.getBeginingDate() == null || challenge.getEndingDate() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        Challenge savedChallenge = challengeRepository.save(challenge);
        return ResponseEntity.ok(savedChallenge);
    }

    // Récupérer tous les défis
    @GetMapping("/all")
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();
        return ResponseEntity.ok(challenges);
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
}
