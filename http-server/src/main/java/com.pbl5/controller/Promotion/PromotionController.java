package com.pbl5.controller.Promotion;

import com.pbl5.dao.IPromotionDAO;
import com.pbl5.dao.impl.PromotionDAO;
import com.pbl5.helpers.Http;
import com.pbl5.helpers.SaveFile;
import com.pbl5.models.Promotion;
import com.pbl5.service.IPromotionService;
import com.pbl5.service.impl.PromotionService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 + "/promotion"})
@MultipartConfig
public class PromotionController extends HttpServlet {
    private IPromotionDAO promotionDAO = new PromotionDAO();
    private IPromotionService promotionService = new PromotionService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Promotion promotion = Http.paramsToString(req.getParameterMap()).toModel(Promotion.class);
        ErrorHandler.handle(resp, () -> promotionService.findOneById(promotion.getId()));


    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("update promotion");
       Promotion promotion = Http.paramsToString(req.getParameterMap()).toModel(Promotion.class);
        Promotion promotionOld = promotionDAO.findOneById(promotion.getId());
        if (promotionOld.getId() != null) {
            if (req.getPart("image") != null ) {
                System.out.println("request"+ req.getPart("image"));
                System.out.println("co gui anh");
                System.out.println("poster cu : "+promotionOld.getImage());
                boolean isImageDelete = SaveFile.delete(promotionOld.getImage());
                if (isImageDelete) {
                    System.out.println("da xoa anh");
                    String path = SaveFile.save(req, "image");
                    System.out.println("poster moi : "+path);

                    promotion.setImage(path);
                }
            } else {
                promotion.setImage(promotionOld.getImage());
            }
            ErrorHandler.handle(resp, () -> promotionService.updatePromotion(promotion));
        }
    }
}
