package com.pbl5.service;

import com.pbl5.dtos.Pagination.BookingPaginationDTO;
import com.pbl5.helpers.respone.Message;
import com.pbl5.models.Booking;

import java.util.List;

public interface IBookingService {
    Message createBooking(Booking booking);
    Message findSeatAndStatusByShowTimeId(String showTimeId);
    Message changeStatus(String bookingId, String status);
    Message deleteBooking(String bookingId);
    Message findByUserId(BookingPaginationDTO booking);

     Message orderReturn(String orderInfo,String paymentTime,String transactionId,String totalPrice,int status);
}
