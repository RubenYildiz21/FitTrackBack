package com.fittrack.fit_track.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fittrack.fit_track.dto.CommentaireDTO;
import com.fittrack.fit_track.mapper.CommentaireMapper;
import com.fittrack.fit_track.model.Commentaire;
import com.fittrack.fit_track.model.Post;
import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.CommentaireRepository;
import com.fittrack.fit_track.repository.PostRepository;
import com.fittrack.fit_track.repository.UserRepository;

@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public CommentaireDTO createCommentaire(CommentaireDTO commentaireDTO) {
        Post post = postRepository.findById(commentaireDTO.getPostId())
            .orElseThrow(() -> new RuntimeException("Post not found"));
            
        User user = userRepository.findById(commentaireDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Commentaire commentaire = new Commentaire();
        commentaire.setMessage(commentaireDTO.getMessage());
        commentaire.setDateCommentaire(new Date());
        commentaire.setPost(post);
        commentaire.setUser(user);

        Commentaire savedCommentaire = commentaireRepository.save(commentaire);
        return CommentaireMapper.INSTANCE.commentaireToCommentaireDTO(savedCommentaire);
    }

    public List<CommentaireDTO> getCommentairesByPostId(Long postId) {
        List<Commentaire> commentaires = commentaireRepository.findByPostIdPostOrderByDateCommentaireDesc(postId);
        return commentaires.stream()
                .map(CommentaireMapper.INSTANCE::commentaireToCommentaireDTO)
                .toList();
    }
}