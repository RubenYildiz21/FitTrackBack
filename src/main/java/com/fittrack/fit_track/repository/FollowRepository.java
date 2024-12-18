package com.fittrack.fit_track.repository;

import com.fittrack.fit_track.model.Follow;
import com.fittrack.fit_track.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollow(User follow);

    Optional<Follow> findByFollowerAndFollow(User follower, User follow);

    // nombre de followers d'un utilisateur
    long countByFollow(User follow);

    // nombre d'utilisateurs suivis par un utilisateur
    long countByFollower(User follower);

    // verifier si il follow deja l'user
    boolean existsByFollowerAndFollow(User currentUser, User userToFollow);

    @Query("SELECT f.follow.id FROM Follow f WHERE f.follower.id = :followerId")
    List<Long> findFollowedUserIds(@Param("followerId") Long followerId);
}
