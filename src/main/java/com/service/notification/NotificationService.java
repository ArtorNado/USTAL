package com.service.notification;

import com.dto.MessageDto;
import com.dto.NotificationDto;

public interface NotificationService {

    NotificationDto getMessageByRecipientId(Integer recipientId);

    MessageDto sendNotification(NotificationDto notification);

    MessageDto sendNotificationForTeam(NotificationDto notification);

    MessageDto answerNotifications(Integer notificationId, Integer answer);
}
