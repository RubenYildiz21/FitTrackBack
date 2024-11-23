package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.PostDTO;
import com.fittrack.fit_track.model.Post;

@Mapper(uses = {CommentaireMapper.class})
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    @Mapping(source = "user.profilePicture", target = "userProfilePicture") // Ajouté
    PostDTO postToPostDTO(Post post);
    
    @Mapping(source = "userId", target = "user.id")
    Post postDTOToPost(PostDTO postDTO);
}
