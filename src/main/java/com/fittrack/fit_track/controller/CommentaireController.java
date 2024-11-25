package com.fittrack.fit_track.controller;

import java.util.List;

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

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentaireDTO> createCommentaire(@RequestBody CommentaireDTO commentaireDTO) {
        try {
            CommentaireDTO createdCommentaire = commentaireService.createCommentaire(commentaireDTO);
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