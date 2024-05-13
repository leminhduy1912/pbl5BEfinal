package com.pbl5.controller.VNPay;

import com.pbl5.configs.VNPayService;
import com.pbl5.dtos.SubmitOrderDTO;
import com.pbl5.helpers.Http;
import com.pbl5.models.Movie;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 + "/submitOrder"})
@MultipartConfig
public class Submit extends HttpServlet {
    private VNPayService vnPayService = new VNPayService();
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        SubmitOrderDTO dto = Http.paramsToString(request.getParameterMap()).toModel(SubmitOrderDTO.class);
      String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        System.out.println("base url :"+ baseUrl);
        ErrorHandler.handle(resp, () -> vnPayService.createOrder(dto.getAmount(), dto.getOrderInfo(), baseUrl));
    }
}
