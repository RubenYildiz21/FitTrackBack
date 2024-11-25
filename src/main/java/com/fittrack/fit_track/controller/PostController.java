package com.fittrack.fit_track.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fittrack.fit_track.dto.PostDTO;
import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.mapper.PostMapper;
import com.fittrack.fit_track.mapper.UserMapper;
import com.fittrack.fit_track.model.Post;
import com.fittrack.fit_track.service.CloudinaryService;
import com.fittrack.fit_track.service.PostService;
import com.fittrack.fit_track.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        try {
            List<PostDTO> postDTOs = postService.getAllPosts();
            return ResponseEntity.ok(postDTOs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        try {
            PostDTO postDTO = postService.getPostById(id);
            return ResponseEntity.ok(postDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDTO> createPost(
            @RequestParam("contenu") String contenu,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("userId") Long userId) {
        try {
            // Récupérer l'utilisateur
            UserDTO userDTO = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

            // Créer le PostDTO
            PostDTO postDTO = new PostDTO();
            postDTO.setContenu(contenu);
            postDTO.setUserId(userId);

            // Upload de l'image si présente
            if (image != null && !image.isEmpty()) {
                String imageUrl = cloudinaryService.uploadImage(image);
                postDTO.setImageUrl(imageUrl);
            }

            // Créer le post
            PostDTO createdPostDTO = postService.createPost(postDTO, userMapper.userDTOToUser(userDTO));
            return new ResponseEntity<>(createdPostDTO, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @PostMapping("/{id}/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDTO> likePost(@PathVariable Long id, @RequestParam Long userId) {
        try {
            PostDTO updatedPost = postService.likePost(id, userId);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDTO> unlikePost(@PathVariable Long id, @RequestParam Long userId) {
        try {
            PostDTO updatedPost = postService.unlikePost(id, userId);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/hasLiked")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> hasUserLikedPost(@PathVariable Long id, @RequestParam Long userId) {
        boolean hasLiked = postService.hasUserLikedPost(id, userId);
        return ResponseEntity.ok(hasLiked);
    }


}