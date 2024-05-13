package com.pbl5.service;

import com.pbl5.dtos.Pagination.NotificationPaginationDTO;
import com.pbl5.helpers.respone.Message;

public interface INotificationService {
    Message findAllPagination(NotificationPaginationDTO dto);
}
