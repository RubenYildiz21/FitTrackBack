package com.fittrack.fit_track.mapper;

import com.fittrack.fit_track.dto.ChallengeDTO;
import com.fittrack.fit_track.model.Challenge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {

    @Mappings({
            @Mapping(source = "idUser", target = "id_user"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "beginingDate", target = "beginDate"),
            @Mapping(source = "endingDate", target = "endDate")
    })
    ChallengeDTO challengeToDTO(Challenge challenge);

    @Mappings({
            @Mapping(source = "id_user", target = "idUser"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "beginDate", target = "beginingDate"),
            @Mapping(source = "endDate", target = "endingDate")
    })
    Challenge challengeDTOToChallenge(ChallengeDTO challengeDTO);

    @org.mapstruct.Named("localDateToLocalDateTime")
    default LocalDateTime localDateToLocalDateTime(LocalDate date) {
        return date == null ? null : date.atStartOfDay();
    }

    @org.mapstruct.Named("localDateTimeToLocalDate")
    default LocalDate localDateTimeToLocalDate(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.toLocalDate();
    }
}
