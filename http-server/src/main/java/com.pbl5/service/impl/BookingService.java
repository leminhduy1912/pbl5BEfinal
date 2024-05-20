package com.pbl5.service.impl;

import com.pbl5.dao.IBookingDAO;
import com.pbl5.dao.impl.BookingDAO;
import com.pbl5.dtos.BookingDTO;
import com.pbl5.dtos.OrderReturnDTO;
import com.pbl5.dtos.Pagination.BookingPaginationDTO;
import com.pbl5.dtos.Pagination.MoviePaginationDTO;
import com.pbl5.helpers.Http;
import com.pbl5.helpers.IDGeneration;
import com.pbl5.helpers.respone.*;
import com.pbl5.models.Booking;
import com.pbl5.models.Movie;
import com.pbl5.service.IBookingService;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class BookingService implements IBookingService {
    private IBookingDAO bookingDAO = new BookingDAO();

    @Override
    public Message createBooking(Booking booking) {

       Booking checkSeatExist = bookingDAO.checkStatusSeatByShowTimeId(booking);
       if (checkSeatExist != null){

           Meta meta = new Meta.Builder(HttpServletResponse.SC_CONFLICT).withMessage(Response.FAILED).build();
           return new Message.Builder(meta).build();
       } else {

           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
           LocalDateTime now = LocalDateTime.now();
           java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(now);
           booking.setCreatedAt(timestamp);
           String id = IDGeneration.generate();
           booking.setId(id);

           try {

               bookingDAO.createBooking(booking);

               Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.CREATED).build();
               Data data = new Data.Builder(null).withResults(id).build();
               return new Message.Builder(meta).withData(data).build();
           } catch (Exception e) {
               e.printStackTrace();
               Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage(Response.CREATE_FAILED).build();
               return new Message.Builder(meta).build();
           }
       }
    }
    // checkStatusSeatByShowTimeId
    @Override
    public Message findSeatAndStatusByShowTimeId(String showTimeId) {

        try {
            List<Booking> bookings = bookingDAO.findSeatAndStatusByShowTimeId( showTimeId);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(bookings).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message changeStatus(String bookingId, String status) {
        try {
            bookingDAO.changeStatus(bookingId,status);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message deleteBooking(String bookingId) {
        try {
            bookingDAO.deleteBooking(bookingId);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message findByUserId(BookingPaginationDTO booking) {
        BookingPaginationDTO domain = Http.objectMapper(booking, BookingPaginationDTO.class);
        List<Booking> results = bookingDAO.findAllBookingFromUser(domain);
        Integer pages = bookingDAO.countAllBooking();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(results).build();
        Pagination pagination = new Pagination.Builder().withCurrentPage(domain.getPage())
                .withTotalPages((int) Math.ceil((double) pages / PER_PAGE))
                .withTotalResults(pages).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
    }

    @Override
    public Message orderReturn(String orderInfo, String paymentTime, String transactionId, String totalPrice, int status) {
        try {
            OrderReturnDTO orderReturnDTO = new OrderReturnDTO();
            if (status==1){
                orderReturnDTO.setOrderInfo(orderInfo);
                orderReturnDTO.setPaymentTime(paymentTime);
                orderReturnDTO.setTransactionId(transactionId);
                orderReturnDTO.setTotalPrice(totalPrice);
                Data data = new Data.Builder(null).withResults(orderReturnDTO).build();
                Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
                return new Message.Builder(meta).withData(data).build();
            }
            else {
                Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
                return new Message.Builder(meta).build();
            }
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }
}
