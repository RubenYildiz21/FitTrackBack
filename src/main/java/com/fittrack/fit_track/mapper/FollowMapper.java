package com.fittrack.fit_track.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fittrack.fit_track.dto.FollowDTO;
import com.fittrack.fit_track.model.Follow;

@Mapper
public interface FollowMapper {
    FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);
    
    @Mapping(source = "follower.id", target = "followerId")
    @Mapping(source = "follow.id", target = "followId")
    FollowDTO followToFollowDTO(Follow follow);
    
    @Mapping(source = "followerId", target = "follower.id")
    @Mapping(source = "followId", target = "follow.id")
    Follow followDTOToFollow(FollowDTO followDTO);
}
