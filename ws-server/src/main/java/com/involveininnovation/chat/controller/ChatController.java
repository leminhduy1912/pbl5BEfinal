package com.involveininnovation.chat.controller;

import com.involveininnovation.chat.entity.NotificationEntity;
import com.involveininnovation.chat.model.Message;
import com.involveininnovation.chat.model.NotificationDTO;
import com.involveininnovation.chat.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
@Autowired
private INotificationService notificationService;

    @MessageMapping("/notification")
    @SendTo("/notification/public")
    public NotificationEntity receiveMessage(@Payload NotificationEntity message){
            System.out.println("message :"+message);
        return notificationService.createNotification(message);
    }
//    @MessageMapping("/private-message")
//    public Message recMessage(@Payload Message message){
//        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
//        System.out.println(message.toString());
//        return message;
//    }
}
