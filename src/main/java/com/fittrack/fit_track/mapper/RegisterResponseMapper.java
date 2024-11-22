package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.RegisterResponseDTO;
import com.fittrack.fit_track.model.User;

@Mapper(uses = {UserMapper.class}, componentModel = "spring")
public interface RegisterResponseMapper {
    RegisterResponseMapper INSTANCE = Mappers.getMapper(RegisterResponseMapper.class);
    
    RegisterResponseDTO toRegisterResponseDTO(User user);
}
