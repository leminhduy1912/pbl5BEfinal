package com.pbl5.controller.showtime;

import com.pbl5.dtos.Pagination.ShowTimePaginationDTO;
import com.pbl5.helpers.Http;
import com.pbl5.models.ShowTime;
import com.pbl5.service.IShowTimeService;
import com.pbl5.service.impl.ShowTimeService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.ADMIN;
import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 +ADMIN+ "/showtime"})
@MultipartConfig
public class ShowTimeController extends HttpServlet {
    private IShowTimeService showTimeService = new ShowTimeService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShowTime showTime = Http.paramsToString(req.getParameterMap()).toModel(ShowTime.class);
        ErrorHandler.handle(resp, () -> showTimeService.createShowTime(showTime,req.getHeader("username")));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShowTimePaginationDTO showTime = Http.paramsToString(req.getParameterMap()).toModel(ShowTimePaginationDTO.class);
        ErrorHandler.handle(resp, () -> showTimeService.findAllWithPagination(showTime));
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShowTime showTime = Http.paramsToString(req.getParameterMap()).toModel(ShowTime.class);
        ErrorHandler.handle(resp, () -> showTimeService.update(showTime));
    }
}
