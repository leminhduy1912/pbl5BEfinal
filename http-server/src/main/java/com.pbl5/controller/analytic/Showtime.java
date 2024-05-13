package com.pbl5.controller.analytic;

import com.pbl5.dtos.UserSignInDTO;
import com.pbl5.helpers.Http;
import com.pbl5.models.Time;
import com.pbl5.service.IAdminService;
import com.pbl5.service.IShowTimeService;
import com.pbl5.service.impl.AdminService;
import com.pbl5.service.impl.ShowTimeService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.*;

@WebServlet(urlPatterns = {V1 + ADMIN + "/analytic/showtime"})
@MultipartConfig
public class Showtime extends HttpServlet {
    private IAdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Time dto = Http.paramsToString(req.getParameterMap()).toModel(Time.class);

        ErrorHandler.handle(resp, () -> adminService.findBookingByMonthAndYear(dto.getMonth(),dto.getYear()));
    }
}
