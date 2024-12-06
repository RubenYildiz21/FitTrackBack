package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.LoginResponseDTO;
import com.fittrack.fit_track.model.User;

@Mapper(uses = {UserMapper.class}, componentModel = "spring")
public interface LoginResponseMapper {
    LoginResponseMapper INSTANCE = Mappers.getMapper(LoginResponseMapper.class);
    
    //@Mapping(source = "user", target = "user")
    LoginResponseDTO toLoginResponseDTO(String token, User user);
}
