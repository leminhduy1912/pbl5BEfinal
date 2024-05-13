package com.pbl5.dao;

import com.pbl5.dtos.NotificationDTO;
import com.pbl5.dtos.Pagination.NotificationPaginationDTO;

import java.util.List;

public interface INotificationDAO {
    List<NotificationDTO> findAllWithPagination(NotificationPaginationDTO dto);
    Integer countAllNotification();
}
