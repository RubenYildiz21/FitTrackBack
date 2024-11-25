package com.fittrack.fit_track.service;

import java.util.List;
import  java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fittrack.fit_track.dto.PostDTO;
import com.fittrack.fit_track.mapper.PostMapper;
import com.fittrack.fit_track.model.Post;
import com.fittrack.fit_track.model.PostLike;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.PostLikeRepository;
import com.fittrack.fit_track.repository.PostRepository;
import com.fittrack.fit_track.repository.UserRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private UserRepository userRepository; 

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByDateCreationDesc(); // Récupère les posts par date décroissante
        System.out.println("Nombre de posts trouvés : " + posts.size());
        return posts.stream()
                .map(post -> {
                    PostDTO dto = PostMapper.INSTANCE.postToPostDTO(post);
                    System.out.println("Post ID: " + dto.getIdPost() + 
                                     ", Utilisateur: " + dto.getUserFirstName() + 
                                     " " + dto.getUserLastName());
                    return dto;
                })
                .toList();
    }
    
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Post not found"));
        return PostMapper.INSTANCE.postToPostDTO(post);
    }

    public PostDTO createPost(PostDTO postDTO, User user) {
        Post post = PostMapper.INSTANCE.postDTOToPost(postDTO);
        post.setUser(user);
        post.setImageUrl(postDTO.getImageUrl());
        post.setDateCreation(new Date());
        
        Post savedPost = postRepository.save(post);
        return PostMapper.INSTANCE.postToPostDTO(savedPost);
    }
       
    // Mettre à jour un post à partir d'un DTO et retourner le DTO mis à jour
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setContenu(postDTO.getContenu());
        // Mettez à jour d'autres champs si nécessaire
        Post updatedPost = postRepository.save(post);
        return PostMapper.INSTANCE.postToPostDTO(updatedPost);
    }

    // Supprimer un post par ID
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }

    public PostDTO likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found"));
        
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Vérifier si l'utilisateur a déjà liké
        if (postLikeRepository.existsByUserIdAndPostIdPost(userId, postId)) {
            throw new RuntimeException("User has already liked this post");
        }

        // Créer et sauvegarder le like
        PostLike postLike = new PostLike();
        postLike.setPost(post);
        postLike.setUser(user);
        postLikeRepository.save(postLike);
        
        // Mettre à jour le nombre de likes
        post.setNombreLikes(post.getNombreLikes() + 1);
        Post updatedPost = postRepository.save(post);
        
        return PostMapper.INSTANCE.postToPostDTO(updatedPost);
    }

    public PostDTO unlikePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found"));
        
        // Vérifier si l'utilisateur a liké
        if (!postLikeRepository.existsByUserIdAndPostIdPost(userId, postId)) {
            throw new RuntimeException("User hasn't liked this post");
        }
    
        // Trouver et supprimer le like
        PostLike postLike = postLikeRepository.findByUserIdAndPostIdPost(userId, postId);
        if (postLike != null) {
            postLikeRepository.delete(postLike);
            
            // Mettre à jour le nombre de likes
            post.setNombreLikes(Math.max(0, post.getNombreLikes() - 1));
            Post updatedPost = postRepository.save(post);
            return PostMapper.INSTANCE.postToPostDTO(updatedPost);
        }
        
        throw new RuntimeException("Like not found");
    }

    public boolean hasUserLikedPost(Long postId, Long userId) {
        return postLikeRepository.existsByUserIdAndPostIdPost(userId, postId);
    }

  
}