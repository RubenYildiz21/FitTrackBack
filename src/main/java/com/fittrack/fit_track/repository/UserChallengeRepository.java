package com.fittrack.fit_track.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fittrack.fit_track.model.UserChallenge;
import com.fittrack.fit_track.model.UserChallengePK;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, UserChallengePK> {
    List<UserChallenge> findByUserId(Long userId);

    List<UserChallenge> findByChallengeId(Long challengeId);

    Optional<UserChallenge> findByUserIdAndChallengeId(Long userId, Long challengeId);
}
