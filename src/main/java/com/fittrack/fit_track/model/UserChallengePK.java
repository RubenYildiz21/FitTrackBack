package com.fittrack.fit_track.model;

import java.io.Serializable;
import java.util.Objects;

public class UserChallengePK implements Serializable {

    private Long userId;
    private Long challengeId;

    // Constructors, Getters, Setters, hashCode and equals methods

    public UserChallengePK() {}

    public UserChallengePK(Long userId, Long challengeId) {
        this.userId = userId;
        this.challengeId = challengeId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChallengePK that = (UserChallengePK) o;
        return Objects.equals(userId, that.userId) && Objects.equals(challengeId, that.challengeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, challengeId);
    }
}
