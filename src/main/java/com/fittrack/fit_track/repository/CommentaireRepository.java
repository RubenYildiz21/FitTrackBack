package com.fittrack.fit_track.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.fittrack.fit_track.model.Commentaire;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

  @Query("SELECT DISTINCT c FROM Commentaire c " +
           "LEFT JOIN FETCH c.user " +
           "LEFT JOIN FETCH c.replies r " +
           "LEFT JOIN FETCH r.user " +
           "WHERE c.post.idPost = :postId " +
           "AND c.parentComment IS NULL " +
           "ORDER BY c.dateCommentaire DESC")
    List<Commentaire> findByPostIdPostAndParentCommentIsNullOrderByDateCommentaireDesc(@Param("postId") Long postId);
    
}