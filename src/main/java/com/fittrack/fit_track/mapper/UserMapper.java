package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;

import com.fittrack.fit_track.dto.UserDTO;
import com.fittrack.fit_track.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
