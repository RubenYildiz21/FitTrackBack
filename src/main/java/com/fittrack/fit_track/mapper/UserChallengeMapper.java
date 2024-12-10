package com.fittrack.fit_track.mapper;

import com.fittrack.fit_track.dto.ChallengeDTO;
import com.fittrack.fit_track.dto.UserChallengeDTO;
import com.fittrack.fit_track.model.Challenge;
import com.fittrack.fit_track.model.UserChallenge;
import org.mapstruct.Mapper;

import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.model.User;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface UserChallengeMapper {

    @Mappings({
            @Mapping(source = "userId", target = "user_id"),
            @Mapping(source = "challengeId", target = "challenge_id"),
            @Mapping(source = "userScore", target = "user_score"),
    })
    UserChallengeDTO userChallengeToDTO(UserChallenge userChallenge);

    @Mappings({
            @Mapping(source = "user_id", target = "userId"),
            @Mapping(source = "challenge_id", target = "challengeId"),
            @Mapping(source = "user_score", target = "userScore"),
    })
    UserChallenge userChallengeDTOToUserChallenge(UserChallengeDTO userChallengeDTO);
}
