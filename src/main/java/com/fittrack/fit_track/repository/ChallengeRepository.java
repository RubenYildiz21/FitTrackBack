package com.fittrack.fit_track.repository;

import com.fittrack.fit_track.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
