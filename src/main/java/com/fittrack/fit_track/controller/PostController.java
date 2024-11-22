package com.fittrack.fit_track.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.dto.PostDTO;
import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.mapper.PostMapper;
import com.fittrack.fit_track.mapper.UserMapper;
import com.fittrack.fit_track.model.Post;
import com.fittrack.fit_track.service.PostService;
import com.fittrack.fit_track.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        try {
            List<PostDTO> postDTOs = postService.getAllPosts();
            return ResponseEntity.ok(postDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        if (postDTO.getUserId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Récupérer l'utilisateur via UserService
       // Récupérer l'utilisateur via UserService
        UserDTO userDTO = userService.getUserById(postDTO.getUserId())
                                      .orElseThrow(() -> new RuntimeException("User not found"));

        // Mapper PostDTO à Post
        Post post = PostMapper.INSTANCE.postDTOToPost(postDTO);

        // Associer l'utilisateur au post
        PostDTO createdPostDTO = postService.createPost(postDTO, userMapper.userDTOToUser(userDTO));



        return new ResponseEntity<>(createdPostDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        try {
            PostDTO updatedPostDTO = postService.updatePost(id, postDTO);
            return ResponseEntity.ok(updatedPostDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}