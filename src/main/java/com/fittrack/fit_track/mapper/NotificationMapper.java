package com.fittrack.fit_track.mapper;

import com.fittrack.fit_track.dto.NotificationDTO;
import com.fittrack.fit_track.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "from", target = "from"),
            @Mapping(source = "to", target = "to"),
            @Mapping(source = "content", target = "content")
    })
    NotificationDTO notificationToDTO(Notification notification);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "from", target = "from"),
            @Mapping(source = "to", target = "to"),
            @Mapping(source = "content", target = "content")
    })
    Notification notificationDTOToNotification(NotificationDTO notificationDto);

}
