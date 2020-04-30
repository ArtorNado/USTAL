package com.models;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notifications {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "notification_id")
    private Integer notificationId;

    private Integer senderId;

    private Integer recipientId;

    // 1 - Заявка на вступление в команду. Если тип 1, то ид получателя равняется ид команды, которой адрессована заявка
    private Integer notificationType;

    // 3 - заявка рассматривается, 1 - заявка принята, 2 - заявка отклонена
    private Integer notificationStatus;

    public Notifications(Integer senderId, Integer recipientId, Integer notificationType, Integer notificationStatus) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.notificationType = notificationType;
        this.notificationStatus = notificationStatus;
    }

    public Notifications() {
    }

    public Integer getNotificationId() {
        return notificationId;
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
