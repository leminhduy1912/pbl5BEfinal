package com.pbl5.dao;

import com.pbl5.dtos.BookingDTO;
import com.pbl5.dtos.Pagination.BookingPaginationDTO;
import com.pbl5.models.Booking;

import java.awt.print.Book;
import java.util.List;

public interface IBookingDAO {
    void createBooking(Booking booking);
    List<Booking> findSeatAndStatusByShowTimeId(String showtimeId);
    // checkStatusSeatByShowTimeId
    Booking checkStatusSeatByShowTimeId(Booking booking);
    void changeStatus(String bookingId,String status);
    void deleteBooking(String id);
    Booking findOne(String id);
    // find all booking from user
    List<Booking> findAllBookingFromUser(BookingPaginationDTO booking);
    Integer countAllBooking();
//    List<Booking>

}
