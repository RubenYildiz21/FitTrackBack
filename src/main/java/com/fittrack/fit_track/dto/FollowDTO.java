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
}
