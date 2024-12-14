package com.fittrack.fit_track.controller;

import com.fittrack.fit_track.dto.ChallengeDTO;
import com.fittrack.fit_track.dto.NotificationDTO;
import com.fittrack.fit_track.dto.UserChallengeDTO;
import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.mapper.ChallengeMapper;
import com.fittrack.fit_track.mapper.NotificationMapper;
import com.fittrack.fit_track.mapper.UserChallengeMapper;
import com.fittrack.fit_track.mapper.UserMapper;
import com.fittrack.fit_track.model.Challenge;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.model.UserChallenge;
import com.fittrack.fit_track.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private ChallengeMapper challengeMapper;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserChallengeMapper userChallengeMapper;

    @Autowired
    private UserChallengeRepository userChallengeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createChallenge(@RequestBody Challenge challenge) {
        if (challenge.getIdUser() == null || challenge.getTitle() == null || challenge.getExercise() == null ||
                challenge.getBeginingDate() == null || challenge.getEndingDate() == null || challenge.getDescription() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        ChallengeDTO savedChallenge =  challengeMapper.challengeToDTO(challengeRepository.save(challenge));

        UserChallengeDTO userChallengeDTO = new UserChallengeDTO();
        userChallengeDTO.setUser_id(savedChallenge.getId_user());
        userChallengeDTO.setChallenge_id(savedChallenge.getId());
        userChallengeDTO.setUser_score(0L);


        UserChallengeDTO savedUserChallenge = userChallengeMapper.userChallengeToDTO(userChallengeRepository.save(userChallengeMapper.userChallengeDTOToUserChallenge(userChallengeDTO))); ;

        Map<String, Object> response = new HashMap<>();
        response.put("challenge", savedChallenge);
        response.put("userChallenge", savedUserChallenge);

        List<Long> followers = followRepository.findByFollow(userRepository.findById(savedChallenge.getId_user()).get()).stream()
                .map(follow -> follow.getFollower().getId())
                .collect(Collectors.toList());

        followers.forEach(followerId -> {
            NotificationDTO notification = new NotificationDTO();
            notification.setFrom(savedChallenge.getId_user());
            notification.setTo(followerId);
            notification.setContent("a créé un nouveau challenge : " + savedChallenge.getTitle());

            notificationRepository.save(notificationMapper.notificationDTOToNotification(notification));

        });
    
        return ResponseEntity.ok(response);
    }

    // Récupérer tous les défis
    @GetMapping("/all")
    public ResponseEntity<List<ChallengeDTO>> getAllChallenges() {
        List<ChallengeDTO> challenges = challengeRepository.findAll().stream()
                .map(challengeMapper::challengeToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, List<ChallengeDTO>>> getChallengesByUser(@PathVariable Long userId) {

        List<UserChallengeDTO> userChallenges = userChallengeRepository.findByUserId(userId).stream()
                .map(userChallengeMapper::userChallengeToDTO)
                .collect(Collectors.toList());


        List<Long> participatingChallengeIds = userChallenges.stream()
                .map(UserChallengeDTO::getChallenge_id)
                .collect(Collectors.toList());


        List<ChallengeDTO> participatingChallenges = challengeRepository.findAllById(participatingChallengeIds).stream()
                .map(challengeMapper::challengeToDTO)
                .collect(Collectors.toList());


        List<ChallengeDTO> allChallenges = challengeRepository.findAll().stream()
                .map(challengeMapper::challengeToDTO)
                .collect(Collectors.toList());


        List<ChallengeDTO> joinableChallenges = allChallenges.stream()
                .filter(challenge -> !participatingChallengeIds.contains(challenge.getId()))
                .collect(Collectors.toList());


        Map<String, List<ChallengeDTO>> response = new HashMap<>();
        response.put("participating", participatingChallenges);
        response.put("joinable", joinableChallenges);

        return ResponseEntity.ok(response);
    }

    // Récupérer un défi par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getChallengeById(@PathVariable Long id) {

        Optional<ChallengeDTO> challengeOpt = challengeRepository.findById(id).map(challengeMapper::challengeToDTO);
        //Optional<Challenge> challengeOpt = challengeRepository.findById(id);

        if (challengeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Challenge not found");
        }

        return ResponseEntity.ok(challengeOpt.get());
    }

    // Modifier un défi
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge) {

        Optional<ChallengeDTO> challengeOpt = challengeRepository.findById(id).map(challengeMapper::challengeToDTO);

        //Optional<Challenge> challengeOpt = challengeRepository.findById(id);

        if (challengeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Challenge not found");
        }

        ChallengeDTO existingChallenge = challengeOpt.get();
        existingChallenge.setTitle(updatedChallenge.getTitle());
        existingChallenge.setExercise(updatedChallenge.getExercise());
        existingChallenge.setBeginDate(updatedChallenge.getBeginingDate());
        existingChallenge.setEndDate(updatedChallenge.getEndingDate());


        ChallengeDTO savedChallenge = challengeMapper.challengeToDTO(challengeRepository.save(challengeMapper.challengeDTOToChallenge(existingChallenge)));
        //Challenge savedChallenge = challengeRepository.save(existingChallenge);
        return ResponseEntity.ok(savedChallenge);
    }

    // Supprimer un défi
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteChallenge(@PathVariable Long id) {

        Optional<ChallengeDTO> challengeOpt = challengeRepository.findById(id).map(challengeMapper::challengeToDTO);
        //Optional<Challenge> challengeOpt = challengeRepository.findById(id);

        if (challengeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Challenge not found");
        }

        challengeRepository.deleteById(id);
        return ResponseEntity.ok("Challenge deleted successfully");
    }

    @PostMapping("/join")
    @Transactional
    public ResponseEntity<?> joinChallenge(@RequestBody UserChallenge userChallenge) {
        UserChallengeDTO userChallengeDTO = userChallengeMapper.userChallengeToDTO(userChallenge);

        if (userChallengeDTO.getUser_id() == 0 || userChallengeDTO.getChallenge_id() == 0) {
            return ResponseEntity.badRequest().body("User ID and Challenge ID are required");
        }
        userChallengeDTO.setUser_score(0L);
        UserChallengeDTO savedUserChallenge = userChallengeMapper.userChallengeToDTO(userChallengeRepository.save(userChallengeMapper.userChallengeDTOToUserChallenge(userChallengeDTO)));

        List<Long> followers = followRepository.findByFollow(userRepository.findById(userChallengeDTO.getUser_id()).get()).stream()
                .map(follow -> follow.getFollower().getId())
                .collect(Collectors.toList());

        ChallengeDTO challengeDto = challengeMapper.challengeToDTO(challengeRepository.getReferenceById(userChallengeDTO.getChallenge_id()));

        followers.forEach(followerId -> {
            NotificationDTO notification = new NotificationDTO();
            notification.setFrom(userChallengeDTO.getUser_id());
            notification.setTo(followerId);
            notification.setContent("a rejoint le challenge : " + challengeDto.getTitle());

            notificationRepository.save(notificationMapper.notificationDTOToNotification(notification));

        });
        return ResponseEntity.ok(savedUserChallenge);
    }


    @GetMapping("/leaderBoard/{challengeId}")
    public ResponseEntity<List<Map<String, Object>>> getLeaderBoard(@PathVariable Long challengeId) {

        List<UserChallengeDTO> participatingUsers = userChallengeRepository.findByChallengeId(challengeId).stream()
                .map(userChallengeMapper::userChallengeToDTO)
                .collect(Collectors.toList());

        //List<UserChallenge> participatingUsers = userChallengeRepository.findByChallengeId(challengeId);

        if (participatingUsers.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>()); // Return an empty list if no participants
        }

        // Map each user to their score
        List<Map<String, Object>> leaderboard = participatingUsers.stream().map(userChallenge -> {
                    Optional<User> user = userRepository.findById(userChallenge.getUser_id()); ;

                    if (user.isPresent()) {
                        UserDTO userDTO = userMapper.userToUserDTO(user.get());
                        Map<String, Object> entry = new HashMap<>();
                        entry.put("name", userDTO.getFirstName() + " " + userDTO.getLastName());
                        entry.put("score", userChallenge.getUser_score());
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

        UserChallengeDTO userChallengeDTO = userChallengeMapper.userChallengeToDTO(userChallenge);

        if (userChallengeDTO.getUser_id() ==0  || userChallengeDTO.getChallenge_id() == 0) {
            return ResponseEntity.badRequest().body("User ID and Challenge ID are required");
        }

        userChallengeRepository.delete(userChallengeMapper.userChallengeDTOToUserChallenge(userChallengeDTO));
        return ResponseEntity.ok(userChallengeDTO);}

}
