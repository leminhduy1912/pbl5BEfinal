package com.duc.vnpaydemo.controllers;

import com.duc.vnpaydemo.models.Notification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.sql.Date;

@Controller
public class NotificationController {

    @MessageMapping("/noti")
    @SendTo("/topic/notifications")
    public String sendNotification(@Payload String notification) throws InterruptedException {
        //Thread.sleep(1000);
//        notification.setCreatedAt(new Date());
        System.out.println("receive");
        return notification;
    }
}