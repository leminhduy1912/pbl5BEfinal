package com.pbl5.service.impl;

import com.pbl5.dao.INotificationDAO;
import com.pbl5.dao.impl.NotificationDAO;
import com.pbl5.dtos.NotificationDTO;
import com.pbl5.dtos.Pagination.MoviePaginationDTO;
import com.pbl5.dtos.Pagination.NotificationPaginationDTO;
import com.pbl5.helpers.Http;
import com.pbl5.helpers.respone.*;
import com.pbl5.models.Movie;
import com.pbl5.service.INotificationService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class NotificationService implements INotificationService {
    private INotificationDAO notificationDAO = new NotificationDAO();
    @Override
    public Message findAllPagination(NotificationPaginationDTO dto) {
        NotificationPaginationDTO domain = Http.objectMapper(dto, NotificationPaginationDTO.class);
        List<NotificationDTO> results = notificationDAO.findAllWithPagination(domain);
        Integer pages = notificationDAO.countAllNotification();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(results).build();
        Pagination pagination = new Pagination.Builder().withCurrentPage(domain.getPage())
                .withTotalPages((int) Math.ceil((double) pages / PER_PAGE))
                .withTotalResults(pages).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
    }
}
