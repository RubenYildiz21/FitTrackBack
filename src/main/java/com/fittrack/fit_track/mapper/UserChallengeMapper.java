package com.fittrack.fit_track.mapper;

import com.fittrack.fit_track.dto.ChallengeDTO;
import com.fittrack.fit_track.dto.UserChallengeDTO;
import com.fittrack.fit_track.model.Challenge;
import com.fittrack.fit_track.model.UserChallenge;
import org.mapstruct.Mapper;

import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.model.User;


@Mapper(componentModel = "spring")
public interface UserChallengeMapper {

    UserChallengeDTO userChallengeToDTO(UserChallenge userChallenge);

    UserChallenge userChallengeDTOToUserChallenge(UserChallengeDTO userChallengeDTO);
}
