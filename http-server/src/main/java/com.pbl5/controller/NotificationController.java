package com.pbl5.controller;

import com.pbl5.dtos.Pagination.NotificationPaginationDTO;
import com.pbl5.dtos.Pagination.UserPaginationDTO;
import com.pbl5.helpers.Http;
import com.pbl5.service.INotificationService;
import com.pbl5.service.IUserService;
import com.pbl5.service.impl.NotificationService;
import com.pbl5.service.impl.UserService;
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

@WebServlet(urlPatterns = {V1 +ADMIN+ "/notification"})
@MultipartConfig
public class NotificationController extends HttpServlet {
    private INotificationService notiService = new NotificationService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NotificationPaginationDTO dto = Http.paramsToString(req.getParameterMap()).toModel(NotificationPaginationDTO.class);
        ErrorHandler.handle(resp, () -> notiService.findAllPagination(dto));
    }
}
