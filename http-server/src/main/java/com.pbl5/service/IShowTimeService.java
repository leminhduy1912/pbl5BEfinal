package com.pbl5.service;

import com.pbl5.dtos.Pagination.ShowTimePaginationDTO;
import com.pbl5.helpers.respone.Message;
import com.pbl5.models.Booking;
import com.pbl5.models.ShowTime;

public interface IShowTimeService {
    // find fixture
    Message findByMovieIdAndDateShow(String movieId,String dateShow);
    Message createShowTime(ShowTime showTime,String username);
    Message findAllWithPagination(ShowTimePaginationDTO dto);
    Message update (ShowTime dto);
    Message findByDateShowAndMovieNameAndStatus(ShowTime dto);

}
