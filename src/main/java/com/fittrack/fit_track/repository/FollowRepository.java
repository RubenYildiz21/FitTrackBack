package com.fittrack.fit_track.repository;

import com.fittrack.fit_track.model.Follow;
import com.fittrack.fit_track.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollow(User follow);

    Optional<Follow> findByFollowerAndFollow(User follower, User follow);

    // nombre de followers d'un utilisateur
    long countByFollow(User follow);

    // nombre d'utilisateurs suivis par un utilisateur
    long countByFollower(User follower);
}
