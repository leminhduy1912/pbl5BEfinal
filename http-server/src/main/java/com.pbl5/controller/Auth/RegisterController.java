package com.pbl5.controller.Auth;


import com.pbl5.utils.SendEmail;
import com.pbl5.dtos.UserDTO;
import com.pbl5.helpers.Http;
import com.pbl5.models.User;
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

@WebServlet(urlPatterns = {V1 + AUTH + "/register"})
@MultipartConfig
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 5425347944387647554L;

    private static final IAuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = Http.paramsToString(req.getParameterMap()).toModel(UserDTO.class);
        ErrorHandler.handle(resp, () -> authService.Register(userDTO));
    }

}
