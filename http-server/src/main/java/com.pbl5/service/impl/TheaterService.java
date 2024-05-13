package com.pbl5.service.impl;

import com.pbl5.dao.ITheaterDAO;
import com.pbl5.dao.impl.TheaterDAO;
import com.pbl5.dtos.Pagination.TheaterPaginationDTO;
import com.pbl5.dtos.Pagination.UserPaginationDTO;
import com.pbl5.helpers.Http;
import com.pbl5.helpers.respone.*;
import com.pbl5.models.Theater;
import com.pbl5.models.User;
import com.pbl5.service.ITheaterService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class TheaterService implements ITheaterService {
    private ITheaterDAO theaterDAO = new TheaterDAO();
    @Override
    public Message findAllRoomWithPagination(TheaterPaginationDTO dto) {
        TheaterPaginationDTO domain = Http.objectMapper(dto, TheaterPaginationDTO.class);
        List<Theater> results = theaterDAO.findAllRoomWithPagination(domain);
        Integer pages = theaterDAO.countAllRoom();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(results).build();
        Pagination pagination = new Pagination.Builder().withCurrentPage(domain.getPage())
                .withTotalPages((int) Math.ceil((double) pages / PER_PAGE))
                .withTotalResults(pages).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();

    }

    @Override
    public Message changeStatus(Theater theater) {
        try {
            theaterDAO.changeStatus(theater.getId(), theater.getStatus());
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }
}
