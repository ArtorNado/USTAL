package com.service.notification;

import com.dto.MessageDto;
import com.dto.NotificationDto;
import com.models.Notifications;

import java.util.List;

public interface NotificationService {

    List<Notifications> getMessageByRecipientId(Integer recipientId);

    MessageDto sendNotification(NotificationDto notification);

    MessageDto sendNotificationForTeam(NotificationDto notification);

    MessageDto answerNotifications(Integer notificationId, Integer answer);
}
