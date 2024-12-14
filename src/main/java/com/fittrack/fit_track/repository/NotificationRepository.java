package com.fittrack.fit_track.repository;

import com.fittrack.fit_track.dto.NotificationDTO;
import com.fittrack.fit_track.model.Challenge;
import com.fittrack.fit_track.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {


    List<Notification> findByTo(Long userId);
}
