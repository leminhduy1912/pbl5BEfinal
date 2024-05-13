package com.pbl5.controller.Seat;

import com.pbl5.helpers.Http;
import com.pbl5.models.Seat;
import com.pbl5.service.ISeatService;
import com.pbl5.service.impl.SeatService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 + "/seat"})
@MultipartConfig
public class SeatController extends HttpServlet {
    private ISeatService seatService = new SeatService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Seat seat = Http.paramsToString(req.getParameterMap()).toModel(Seat.class);
        ErrorHandler.handle(resp, () -> seatService.findAllSeatByTheaterId(seat.getTheaterId()));
    }
}
