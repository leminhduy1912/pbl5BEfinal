package com.pbl5.controller.Theater;

import com.pbl5.dtos.Pagination.TheaterPaginationDTO;
import com.pbl5.dtos.Pagination.UserPaginationDTO;
import com.pbl5.helpers.Http;
import com.pbl5.models.Theater;
import com.pbl5.service.ITheaterService;
import com.pbl5.service.impl.TheaterService;
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

@MultipartConfig
@WebServlet(urlPatterns = {V1 +ADMIN+ "/theater"})
public class TheaterController extends HttpServlet {
    private ITheaterService theaterService = new TheaterService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TheaterPaginationDTO dto = Http.paramsToString(req.getParameterMap()).toModel(TheaterPaginationDTO.class);
        ErrorHandler.handle(resp, () -> theaterService.findAllRoomWithPagination(dto));
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Theater dto = Http.paramsToString(req.getParameterMap()).toModel(Theater.class);
        ErrorHandler.handle(resp, () -> theaterService.changeStatus(dto));
    }
}
