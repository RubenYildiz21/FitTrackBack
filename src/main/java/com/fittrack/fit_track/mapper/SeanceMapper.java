package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.SeanceDTO;
import com.fittrack.fit_track.model.Seance;

@Mapper(uses = {BlocMapper.class})
public interface SeanceMapper {
    SeanceMapper INSTANCE = Mappers.getMapper(SeanceMapper.class);
    
    @Mapping(source = "user.id", target = "userId")
    SeanceDTO seanceToSeanceDTO(Seance seance);
    
    @Mapping(target = "user", ignore = true)
    Seance seanceDTOToSeance(SeanceDTO seanceDTO);
}
