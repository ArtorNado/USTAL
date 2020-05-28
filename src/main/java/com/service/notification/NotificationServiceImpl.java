package com.service.notification;

import com.dto.MessageDto;
import com.dto.NotificationAnswerDto;
import com.dto.NotificationDto;
import com.dto.UserIdNamesDto;
import com.models.Notifications;
import com.models.Teams;
import com.models.UserData;
import com.repository.NotificationRepository;
import com.repository.TeamsRepository;
import com.repository.UserDataRepository;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.accessibility.AccessibleAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
/*
@Scope(scopeName = "tenant")
*/
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    TeamsRepository teamsRepository;


    @Override
    public List<NotificationAnswerDto> getMessageByRecipientId(Integer recipientId) {
        Optional<List<Notifications>> notificationFromDb = notificationRepository.getNotificationsByRecipientId(recipientId);
        if (notificationFromDb.isPresent()) {
            List<Notifications> not = notificationFromDb.get();
            List<NotificationAnswerDto> answer = new ArrayList<NotificationAnswerDto>();
            for(Notifications n : not){
                NotificationAnswerDto nad = new NotificationAnswerDto();
                Optional<UserData> senderData = userDataRepository.findUserDataByUserId(n.getSenderId());
                if (senderData.isPresent()) {
                    nad.setSenderData(new UserIdNamesDto(senderData.get().getUserId(), senderData.get().getUserFirstName(),
                            senderData.get().getUserSecondName()));
                } else {
                    nad.setSenderData(null);
                }
                nad.setNotificationId(n.getNotificationId());
                nad.setSenderId(n.getSenderId());
                nad.setRecipientId(n.getRecipientId());
                nad.setNotificationType(n.getNotificationType());
                nad.setNotificationStatus(n.getNotificationStatus());
                answer.add(nad);
                System.out.println(nad + "NAD");
                System.out.println(answer.size());
            }
            System.out.println(answer.toString());
            return answer;
        } else throw new AccessDeniedException("Notifications not found");
    }

    @Override
    @RequestScope
    public MessageDto sendNotification(NotificationDto notification) {
        switch (notification.getNotificationType()) {
            case 1: {
                return sendNotificationForTeam(notification);
            }
            default:
                return new MessageDto("failed");
        }
    }

    @Override
    public MessageDto sendNotificationForTeam(NotificationDto notification) {
        System.out.println(notification.getSenderId());
        Optional<UserData> senderData = userDataRepository.findUserDataByUserId(notification.getSenderId());
        System.out.println(senderData.get().toString());
        System.out.println("STEP1");
        if(senderData.isPresent()) {
            System.out.println("STEP2");
            if(senderData.get().getTeam() == null) {
                System.out.println("STEP3");
                Notifications newNotification = new Notifications(notification.getSenderId(), notification.getRecipientId(),
                        notification.getNotificationType(), notification.getNotificationStatus());
                notificationRepository.save(newNotification);
            } else throw new IllegalArgumentException("Вы уже состоите в команде");
        } else throw new AccessDeniedException("Пользователь не найден");
        return new MessageDto("succsessful");
    }

    private Boolean checkCorrectUserForTeam(Optional<UserData> userDataFromDb){
        try {
            if (userDataFromDb.isPresent()) {
                if (userDataFromDb.get().getTeam().getTeamId() != null) {
                    return false;
                } else return true;
            } else return false;
        }catch (NullPointerException e){
            return true;
        }
    }

    @Override
    public MessageDto answerNotifications(Integer notificationId, Integer answer) {
        Optional<Notifications> notificationFromDb = notificationRepository.findNotificationsByNotificationId(notificationId);
        if(notificationFromDb.isPresent()){
            switch (notificationFromDb.get().getNotificationType()){
                case 1: return  answerNotificationType1(notificationFromDb.get(), answer);
                default: return new MessageDto("failed");
            }
        } else throw new AccessDeniedException("Notification not found");
    }

    public MessageDto answerNotificationType1(Notifications notification, Integer answer) {
        Optional<UserData> userDataFromDb = userDataRepository.findUserDataByUserId(notification.getSenderId());
        if (userDataFromDb.isPresent()) {
            if (checkCorrectUserForTeam(userDataFromDb)) {
                switch (answer) {
                    case 1: {
                        Optional<Teams> teamFromDp = teamsRepository.findTeamsByTeamId(notification.getRecipientId());
                        if (teamFromDp.isPresent()) {
                            userDataFromDb.get().setTeam(teamFromDp.get());
                            userDataRepository.save(userDataFromDb.get());
                            notificationRepository.delete(notification);
                            return new MessageDto("succsessful");
                        } else throw new AccessDeniedException("Команда не найдена");
                    }
                    case 2:{
                        notification.setNotificationStatus(2);
                        notificationRepository.save(notification);
                    }
                    default: return new MessageDto("failed");
                }
            } else{
                notificationRepository.delete(notification);
                throw new AccessDeniedException("Пользователь уже состоит в команде");
            }
        } else throw new AccessDeniedException("Пользователь не найден");
    }


}
