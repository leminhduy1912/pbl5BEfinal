package com.pbl5.controller.User;

import com.pbl5.helpers.Http;
import com.pbl5.models.User;
import com.pbl5.service.IUserService;
import com.pbl5.service.impl.UserService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 + "/find-by-email"})
@MultipartConfig
public class FindByEmailController extends HttpServlet {
    private IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User dto = Http.paramsToString(req.getParameterMap()).toModel(User.class);
        ErrorHandler.handle(resp, () -> userService.findByEmail(dto));
    }
}
