package com.pbl5.controller.booking;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl5.dtos.UserSignInDTO;
import com.pbl5.helpers.Http;
import com.pbl5.models.Booking;
import com.pbl5.service.IBookingService;
import com.pbl5.service.impl.BookingService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 + "/booking"})
@MultipartConfig
public class BookingController extends HttpServlet {
    private IBookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Booking booking = Http.paramsToString(req.getParameterMap()).toModel(Booking.class);
        ErrorHandler.handle(resp, () -> bookingService.findSeatAndStatusByShowTimeId(booking.getShowTimeId()));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Booking booking = Http.paramsToString(req.getParameterMap()).toModel(Booking.class);
        System.out.println("user id of booking"+booking.getUserId());
        ErrorHandler.handle(resp, () -> bookingService.createBooking(booking));
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Booking booking = Http.paramsToString(req.getParameterMap()).toModel(Booking.class);
        ErrorHandler.handle(resp, () -> bookingService.deleteBooking(booking.getId()));
    }

}
