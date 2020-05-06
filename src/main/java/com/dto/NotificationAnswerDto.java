package com.dto;

import com.models.UserData;

public class NotificationAnswerDto {

    private Integer notificationId;

    private Integer senderId;

    private Integer recipientId;

    private Integer notificationType;

    private Integer notificationStatus;

    private UserIdNamesDto senderData;

    public NotificationAnswerDto(Integer notificationId, Integer senderId, Integer recipientId, Integer notificationType, Integer notificationStatus, UserIdNamesDto senderData) {
        this.notificationId = notificationId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.notificationType = notificationType;
        this.notificationStatus = notificationStatus;
        this.senderData = senderData;
    }

    public NotificationAnswerDto() {
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public Integer getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Integer notificationType) {
        this.notificationType = notificationType;
    }

    public Integer getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(Integer notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public UserIdNamesDto getSenderData() {
        return senderData;
    }

    public void setSenderData(UserIdNamesDto senderData) {
        this.senderData = senderData;
    }
}
