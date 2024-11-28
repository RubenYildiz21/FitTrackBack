// src/main/java/com/fittrack/fit_track/dto/FollowDTO.java
package com.fittrack.fit_track.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowDTO {

    @Getter
    @Setter
    private Long idConnection;

    @Getter
    @Setter
    private Long followerId;

    @Getter
    @Setter
    private Long followId;
}
