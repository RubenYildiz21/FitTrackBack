package com.fittrack.fit_track.repository;

import com.fittrack.fit_track.model.Challenge;
import com.fittrack.fit_track.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
