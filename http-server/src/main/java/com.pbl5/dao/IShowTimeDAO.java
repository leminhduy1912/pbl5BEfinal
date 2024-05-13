package com.pbl5.dao;

import com.pbl5.dtos.Pagination.ShowTimePaginationDTO;
import com.pbl5.models.ShowTime;
import com.pbl5.service.impl.ShowTimeService;

import java.util.List;

public interface IShowTimeDAO {
     List<ShowTime> getShowTimeByMovieIdAndDate(String movieId, String dateShow);
     void  createShowTime(ShowTime showTime,String username );
     List<ShowTime> findByMovieIdAndDateShow(String movieId,String dateShow);
     List<ShowTime> findAllAPagination(ShowTimePaginationDTO dto);
     void update(ShowTime dto);
     List<ShowTime> findByDateShowAndMovieNameAndStatus(ShowTime dto);
     Integer countAllShowtime();
}
