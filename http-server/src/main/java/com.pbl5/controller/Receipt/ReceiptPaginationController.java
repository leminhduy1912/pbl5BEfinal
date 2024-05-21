package com.pbl5.controller.Receipt;


import com.pbl5.dtos.Pagination.ReceiptPaginationDTO;
import com.pbl5.helpers.Http;
import com.pbl5.service.IReceiptService;
import com.pbl5.service.impl.MovieService;
import com.pbl5.service.impl.ReceiptService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.ADMIN;
import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 +ADMIN+ "/receipts"})
public class ReceiptPaginationController extends HttpServlet {
    private IReceiptService receiptService = new ReceiptService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>> :"+req.getHeader("ACCESS_TOKEN"));



        // find all receipt admin
        ReceiptPaginationDTO dto = Http.paramsToString(req.getParameterMap()).toModel(ReceiptPaginationDTO.class);
        ErrorHandler.handle(resp, () -> receiptService.findAllReceiptPagination(dto));
    }
}
