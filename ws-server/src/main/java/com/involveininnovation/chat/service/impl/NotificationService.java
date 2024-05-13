package com.involveininnovation.chat.service.impl;

import com.involveininnovation.chat.entity.NotificationEntity;
import com.involveininnovation.chat.repository.INotificationRepository;
import com.involveininnovation.chat.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepository;
    @Override
    public NotificationEntity createNotification(NotificationEntity notificationEntity) {
        return notificationRepository.save(notificationEntity);
    }
}
