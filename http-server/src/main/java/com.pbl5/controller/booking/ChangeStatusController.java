package com.pbl5.controller.booking;

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

import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 + "/booking/status"})
@MultipartConfig
public class ChangeStatusController extends HttpServlet {
    private IBookingService bookingService = new BookingService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Booking booking = Http.paramsToString(req.getParameterMap()).toModel(Booking.class);

        ErrorHandler.handle(resp, () -> bookingService.changeStatus(booking.getId(), booking.getStatus()));
    }
}
