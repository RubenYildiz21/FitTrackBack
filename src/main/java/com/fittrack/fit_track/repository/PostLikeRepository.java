package com.fittrack.fit_track.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.fittrack.fit_track.model.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserIdAndPostIdPost(Long userId, Long postId);
    PostLike findByUserIdAndPostIdPost(Long userId, Long postId);
    
}