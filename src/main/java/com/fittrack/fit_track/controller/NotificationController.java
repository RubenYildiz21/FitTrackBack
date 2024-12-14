package com.fittrack.fit_track.controller;

import com.fittrack.fit_track.dto.NotificationDTO;
import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.mapper.NotificationMapper;
import com.fittrack.fit_track.mapper.UserMapper;
import com.fittrack.fit_track.model.Challenge;
import com.fittrack.fit_track.model.Notification;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.model.UserChallenge;
import com.fittrack.fit_track.repository.NotificationRepository;
import com.fittrack.fit_track.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    UserMapper userMapper;


    //private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNotification(@RequestBody Notification notification) {

        NotificationDTO notificationDTO = notificationMapper.notificationToDTO(notification);
        if ( notificationDTO.getTo() == null || notificationDTO.getFrom() == null || notificationDTO.getContent() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        //List<UserDTO> followers =
        NotificationDTO savedNotification = notificationMapper.notificationToDTO(notificationRepository.save(notificationMapper.notificationDTOToNotification(notificationDTO)));

        return ResponseEntity.ok(savedNotification);
    }


    @GetMapping("/user/{to}")
    public ResponseEntity<?> getChallengesByUser(@PathVariable Long to) {

        List<NotificationDTO> notifications = notificationRepository.findByTo(to).stream()
                .map(notificationMapper::notificationToDTO)
                .collect(Collectors.toList());


        List<UserDTO> users = new ArrayList<>();

        notifications.forEach(notificationDTO -> {
            UserDTO user = userMapper.userToUserDTO(userRepository.findById(notificationDTO.getFrom()).orElse(null));
            if (user != null) {
                users.add(user);
            }
        });

        Map<String, List<?>> response = new HashMap<>();
        response.put("notifications", notifications);
        response.put("users", users);
        return ResponseEntity.ok(response);
    }


}
