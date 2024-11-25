package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.BlocDTO;
import com.fittrack.fit_track.model.Bloc;

@Mapper(uses = {SeriesMapper.class, ExerciceMapper.class})
public interface BlocMapper {
    BlocMapper INSTANCE = Mappers.getMapper(BlocMapper.class);
    
    @Mapping(source = "seance.idSeance", target = "seanceId")
    BlocDTO blocToBlocDTO(Bloc bloc);
    
    // Exclude mapping seance in DTO to entity since we handle it manually
    @Mapping(target = "seance", ignore = true)
    // Exclude mapping exercice since we handle it manually
    //@Mapping(target = "exercice", ignore = true)
    Bloc blocDTOToBloc(BlocDTO blocDTO);
}
