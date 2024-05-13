package com.pbl5.helpers.mapper;

import com.pbl5.dtos.NotificationDTO;


import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationMapper implements IMapper<NotificationDTO>{
    @Override
    public NotificationDTO mapRow(ResultSet result) throws SQLException {
        NotificationDTO noti = new NotificationDTO();
        try {
            noti.setId(result.getInt("id"));
            noti.setContent(result.getString("content"));
            noti.setDate(result.getTimestamp("date"));
            noti.setSenderId(result.getString("sender_id"));
            noti.setSenderName(result.getString("sender_name"));
            return noti;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return null;
        }
    }
}
