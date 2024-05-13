package com.pbl5.dao.impl;

import com.pbl5.dao.INotificationDAO;
import com.pbl5.dtos.NotificationDTO;
import com.pbl5.dtos.Pagination.NotificationPaginationDTO;
import com.pbl5.helpers.mapper.CountMapper;
import com.pbl5.helpers.mapper.MovieMapper;
import com.pbl5.helpers.mapper.NotificationMapper;
import com.pbl5.models.Promotion;

import java.util.List;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class NotificationDAO extends AbstractDAO<NotificationDTO> implements INotificationDAO {
    @Override
    public List<NotificationDTO> findAllWithPagination(NotificationPaginationDTO dto) {
        String sql="SELECT * FROM notifications ORDER BY date DESC  LIMIT "
                +PER_PAGE+" OFFSET " + (dto.getPage() - 1) * PER_PAGE;
        return query(sql, new NotificationMapper());
    }

    @Override
    public Integer countAllNotification() {
        String sql = "SELECT COUNT(*) as total FROM notifications ";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }
}
