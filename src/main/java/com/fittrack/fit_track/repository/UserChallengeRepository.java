package com.fittrack.fit_track.repository;

import com.fittrack.fit_track.model.Challenge;
import com.fittrack.fit_track.model.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    List<UserChallenge> findByUserId(Long userId);

    List<UserChallenge> findByChallengeId(Long challengeId);

    Optional<UserChallenge> findByUserIdAndChallengeId(Long userId, Long challengeId);
}
