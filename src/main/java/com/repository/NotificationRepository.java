package com.repository;

import com.models.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notifications, Integer> {

    Optional<List<Notifications>> getNotificationsByRecipientId(Integer recipientId);

    Optional<Notifications> findNotificationsByNotificationId(Integer notificationId);

    void deleteNotificationsByNotificationId(Integer notification);

    Optional<List<Notifications>> getNotificationsByRecipientIdAndSenderId(Integer recipient, Integer sender);

}
