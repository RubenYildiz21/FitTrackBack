package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.DefiDTO;
import com.fittrack.fit_track.model.Defi;

@Mapper
public interface DefiMapper {
    DefiMapper INSTANCE = Mappers.getMapper(DefiMapper.class);
    
    @Mapping(source = "createur.id", target = "createurId")
    @Mapping(source = "createur.firstName", target = "createurFirstName")
    @Mapping(source = "createur.lastName", target = "createurLastName")
    DefiDTO defiToDefiDTO(Defi defi);
    
    Defi defiDTOToDefi(DefiDTO defiDTO);
}
