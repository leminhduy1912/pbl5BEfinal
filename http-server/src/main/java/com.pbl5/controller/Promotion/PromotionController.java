package com.pbl5.controller.Promotion;

import com.pbl5.helpers.Http;
import com.pbl5.models.Promotion;
import com.pbl5.service.IPromotionService;
import com.pbl5.service.impl.PromotionService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 + "/promotion"})
public class PromotionController extends HttpServlet {
    private IPromotionService promotionService = new PromotionService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Promotion promotion = Http.paramsToString(req.getParameterMap()).toModel(Promotion.class);
        ErrorHandler.handle(resp, () -> promotionService.findOneById(promotion.getId()));
    }
}
