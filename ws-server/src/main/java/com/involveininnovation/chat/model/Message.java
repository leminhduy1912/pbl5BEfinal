package com.involveininnovation.chat.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    private String senderId;
    private String senderName;
    private String content;
    private Timestamp date;


}
