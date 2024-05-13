package com.pbl5.service.impl;

import com.pbl5.dao.IPromotionDAO;
import com.pbl5.dao.impl.PromotionDAO;
import com.pbl5.helpers.IDGeneration;
import com.pbl5.helpers.respone.Data;
import com.pbl5.helpers.respone.Message;
import com.pbl5.helpers.respone.Meta;
import com.pbl5.helpers.respone.Response;
import com.pbl5.models.Movie;
import com.pbl5.models.Promotion;
import com.pbl5.service.IPromotionService;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionService implements IPromotionService {
    private IPromotionDAO promotionDAO = new PromotionDAO();
    @Override
    public Message findAll() {
        try {
            List<Promotion> promotions = promotionDAO.findAll();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(promotions).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message findOneById(String id) {
        try {
            Promotion promotion = promotionDAO.findOneById(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(promotion).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message findAllImage() {
        try {
            List<Promotion> promotionImages = promotionDAO.findAllImage();

            List<Map<String, String>> results = new ArrayList<>();
            for (Promotion promotion : promotionImages){
                Map<String, String> paths = new HashMap<>();
                paths.put("id", promotion.getId());
                paths.put("path", promotion.getImage());
                results.add(paths);
            }
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(results).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }
    @Override
    public Message createPromotion(Promotion promotion,String username) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(now);
        promotion.setCreatedAt(timestamp);

        String id = IDGeneration.generate();
        promotion.setId(id);

        try {
            promotionDAO.createPromotion(promotion, username);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();

        } catch (Exception e) {

            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage(Response.CREATE_FAILED).build();
            return new Message.Builder(meta).build();

        }
    }
}
