package com.service.notification;

import com.dto.MessageDto;
import com.dto.NotificationAnswerDto;
import com.dto.NotificationDto;
import com.models.Notifications;

import java.util.List;

public interface NotificationService {

    List<NotificationAnswerDto> getMessageByRecipientId(Integer recipientId);

    MessageDto sendNotification(NotificationDto notification);

    MessageDto sendNotificationForTeam(NotificationDto notification);

    MessageDto answerNotifications(Integer notificationId, Integer answer);
}
