package com.controller;

import com.dto.MessageDto;
import com.dto.NotificationDto;
import com.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/sendNotification")
    public ResponseEntity<MessageDto> sendNotification(@RequestBody NotificationDto notification){
        return ResponseEntity.ok(notificationService.sendNotification(notification));}

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/getNotificationByRecipient/{id}")
    public ResponseEntity<NotificationDto> getNotificationByRecipientId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(notificationService.getMessageByRecipientId(id));}

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/answerNotification")
    public ResponseEntity<MessageDto> determineUserStatusInTeam(@RequestParam Integer notification, Integer answer){
        return ResponseEntity.ok(notificationService.answerNotifications(notification, answer));
    }
}
