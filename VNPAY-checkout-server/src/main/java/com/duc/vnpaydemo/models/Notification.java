package com.duc.vnpaydemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {
    private String id;
    private String from;
    private String content;
//   private Date createdAt;

}
