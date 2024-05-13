package com.pbl5.controller.Receipt;

import com.pbl5.dtos.ReceiptDTO;
import com.pbl5.helpers.Http;
import com.pbl5.helpers.SaveFile;
import com.pbl5.models.Promotion;
import com.pbl5.service.IPromotionService;
import com.pbl5.service.IReceiptService;
import com.pbl5.service.impl.PromotionService;
import com.pbl5.service.impl.ReceiptService;
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

@WebServlet(urlPatterns = {V1  + "/receipt"})
@MultipartConfig
public class ReceiptController extends HttpServlet {
    private IReceiptService receiptService= new ReceiptService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceiptDTO receipt = Http.paramsToString(req.getParameterMap()).toModel(ReceiptDTO.class);
        ErrorHandler.handle(resp, () -> receiptService.createReceipt(receipt));
    }
}
