package com.involveininnovation.chat.service;

import com.involveininnovation.chat.entity.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INotificationService {
    NotificationEntity createNotification(NotificationEntity notificationEntity);
    Page<NotificationEntity> findAllPaginationAndSortByDate(Pageable pageable);
}
