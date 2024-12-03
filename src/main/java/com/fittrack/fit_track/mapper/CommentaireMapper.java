package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.CommentaireDTO;
import com.fittrack.fit_track.model.Commentaire;

@Mapper
public interface CommentaireMapper {
    CommentaireMapper INSTANCE = Mappers.getMapper(CommentaireMapper.class);
    
    @Mapping(source = "post.idPost", target = "postId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    @Mapping(source = "user.profilePicture", target = "userProfilePicture") 

    @Mapping(source = "parentComment.idCommentaire", target = "parentCommentId")
    @Mapping(source = "replies", target = "replies")
    CommentaireDTO commentaireToCommentaireDTO(Commentaire commentaire);
    
    @Mapping(source = "postId", target = "post.idPost")
    @Mapping(source = "userId", target = "user.id")

    @Mapping(source = "parentCommentId", target = "parentComment.idCommentaire")
    @Mapping(target = "replies", ignore = true)
    Commentaire commentaireDTOToCommentaire(CommentaireDTO commentaireDTO);
}
