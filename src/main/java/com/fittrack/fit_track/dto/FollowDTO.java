// src/main/java/com/fittrack/fit_track/dto/FollowDTO.java
package com.fittrack.fit_track.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowDTO {

    private Long idConnection;

    private Long followerId;

    private Long followId;

    public Long getIdConnection() {
        return idConnection;
    }

    public void setIdConnection(Long idConnection) {
        this.idConnection = idConnection;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }
}
