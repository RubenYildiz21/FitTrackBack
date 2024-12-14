package com.fittrack.fit_track.controller;

import java.util.List;

import com.fittrack.fit_track.dto.NotificationDTO;
import com.fittrack.fit_track.mapper.NotificationMapper;
import com.fittrack.fit_track.repository.NotificationRepository;
import com.fittrack.fit_track.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fittrack.fit_track.dto.CommentaireDTO;
import com.fittrack.fit_track.service.CommentaireService;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;


    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentaireDTO> createCommentaire(@RequestBody CommentaireDTO commentaireDTO) {
        try {
            CommentaireDTO createdCommentaire = commentaireService.createCommentaire(commentaireDTO);

            Long idPostUser = postService.getPostById(commentaireDTO.getPostId()).getUserId();

            NotificationDTO notification = new NotificationDTO();
            notification.setFrom(commentaireDTO.getUserId());
            notification.setTo(idPostUser);
            notification.setContent("a commenté votre post : " + commentaireDTO.getMessage());

            notificationRepository.save(notificationMapper.notificationDTOToNotification(notification));

            return ResponseEntity.ok(createdCommentaire);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/post/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CommentaireDTO>> getCommentairesByPostId(@PathVariable Long postId) {
        try {
            List<CommentaireDTO> commentaires = commentaireService.getCommentairesByPostId(postId);
            return ResponseEntity.ok(commentaires);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    



}