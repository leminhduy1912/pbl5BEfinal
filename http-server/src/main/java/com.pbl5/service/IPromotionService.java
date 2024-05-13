package com.pbl5.service;

import com.pbl5.helpers.respone.Message;
import com.pbl5.models.Promotion;

import java.util.List;

public interface IPromotionService {
    Message findAll();
    Message findOneById(String id);
    Message findAllImage();
    Message createPromotion(Promotion promotion,String username);
}
