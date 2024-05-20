package com.pbl5.service.impl;


import com.pbl5.dao.IShowTimeDAO;
import com.pbl5.dao.impl.ShowTimeDAO;
import com.pbl5.dtos.Pagination.MoviePaginationDTO;
import com.pbl5.dtos.Pagination.ShowTimePaginationDTO;
import com.pbl5.helpers.Http;
import com.pbl5.helpers.IDGeneration;
import com.pbl5.helpers.respone.*;
import com.pbl5.models.Booking;
import com.pbl5.models.Movie;
import com.pbl5.models.ShowTime;
import com.pbl5.service.IShowTimeService;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class ShowTimeService implements IShowTimeService {
    private IShowTimeDAO showTimeDAO = new ShowTimeDAO();
    // find fixture
    @Override
    public Message findByMovieIdAndDateShow(String movieId, String dateShow) {
        List<ShowTime> result = showTimeDAO.findByMovieIdAndDateShow(movieId, dateShow);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
        Data data = new Data.Builder(null).withResults(result).build();
        return new Message.Builder(meta).withData(data).build();

    }

    @Override
    public Message createShowTime(ShowTime showTime, String username) {

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            showTime.setCreatedAt(timestamp);


            String id = IDGeneration.generate();
            showTime.setId(id);
           showTimeDAO.createShowTime(showTime,username);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch(Exception e){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }

    }

    @Override
    public Message findAllWithPagination(ShowTimePaginationDTO dto) {

        ShowTimePaginationDTO domain = Http.objectMapper(dto, ShowTimePaginationDTO.class);
        List<ShowTime> results = showTimeDAO.findAllAPagination(dto);
        Integer pages = showTimeDAO.countAllShowtime();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(results).build();
        Pagination pagination = new Pagination.Builder().withCurrentPage(domain.getPage())
                .withTotalPages((int) Math.ceil((double) pages / PER_PAGE))
                .withTotalResults(pages).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
    }
    @Override
    public Message update(ShowTime dto) {
        try {
            showTimeDAO.update(dto);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch(Exception e){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message findByDateShowAndMovieNameAndStatus(ShowTime dto) {
        try {
            List<ShowTime> result = showTimeDAO.findByDateShowAndMovieNameAndStatus(dto);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(result).build();
            return new Message.Builder(meta).withData(data).build();
        } catch(Exception e){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }


}
