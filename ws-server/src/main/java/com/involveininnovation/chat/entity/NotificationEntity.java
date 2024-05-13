package com.involveininnovation.chat.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.sql.Timestamp;

@Entity
@Table(name = "notifications")
@Data
public class NotificationEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String senderId;
    private String senderName;
    private String content;
    private Timestamp date;
}
