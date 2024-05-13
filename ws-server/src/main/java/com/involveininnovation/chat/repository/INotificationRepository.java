package com.involveininnovation.chat.repository;

import com.involveininnovation.chat.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends JpaRepository<NotificationEntity,String> {
}
