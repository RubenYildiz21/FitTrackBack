package com.fittrack.fit_track.model;

import jakarta.persistence.*;

@Entity
@IdClass(UserChallengePK.class) // Specify the composite key class
public class UserChallenge {

    @Id
    private Long userId;

    @Id
    private Long challengeId;

    private Long userScore;



    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public Long getUserScore() {
        return userScore;
    }

    public void setUserScore(Long userScore) {
        this.userScore = userScore;
    }
}
