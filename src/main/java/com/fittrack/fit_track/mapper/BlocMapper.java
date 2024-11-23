package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.BlocDTO;
import com.fittrack.fit_track.model.Bloc;

@Mapper(uses= {SeriesMapper.class})
public interface BlocMapper {
    BlocMapper INSTANCE = Mappers.getMapper(BlocMapper.class);
    
    @Mapping(source = "seance.idSeance", target = "seanceId")
    BlocDTO blocToBlocDTO(Bloc bloc);
    
    @Mapping(source = "seanceId", target = "seance.idSeance")
    Bloc blocDTOToBloc(BlocDTO blocDTO);
}
