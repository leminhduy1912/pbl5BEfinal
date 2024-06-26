package com.pbl5.controller.Auth;

import com.pbl5.dtos.UserSignInDTO;
import com.pbl5.helpers.Http;
import com.pbl5.service.IAuthService;
import com.pbl5.service.impl.AuthService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.AUTH;
import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 + AUTH + "/email"})
@MultipartConfig
public class CheckEmailExistController extends HttpServlet {
    private IAuthService authService = new AuthService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSignInDTO dto = Http.paramsToString(req.getParameterMap()).toModel(UserSignInDTO.class);

        ErrorHandler.handle(resp, () -> authService.checkEmailExistAndSendVerifyMailCode(dto.getEmail(), dto.getRoleId()));
    }
}
