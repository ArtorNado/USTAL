package com.dto;

import lombok.Data;

@Data
public class NotificationDto {

    private Integer notificationId;

    private Integer senderId;

    private Integer recipientId;

    private Integer notificationType;

    private Integer notificationStatus;

    public NotificationDto() {
    }

    public NotificationDto(Integer notificationId, Integer senderid, Integer recipientId, Integer notificationType, Integer getNotificationStatus) {
        this.notificationId = notificationId;
        this.senderId = senderid;
        this.recipientId = recipientId;
        this.notificationType = notificationType;
        this.notificationStatus = getNotificationStatus;
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
}
