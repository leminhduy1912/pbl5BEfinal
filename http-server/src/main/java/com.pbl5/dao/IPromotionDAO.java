package com.pbl5.dao;

import com.pbl5.models.Promotion;

import java.util.List;

public interface IPromotionDAO {
    List<Promotion> findAll();
    Promotion findOneById(String id);
    List<Promotion> findAllImage();
    void createPromotion(Promotion promotion,String username);
    void updatePromotion(Promotion promotion);
    void deletePromotion(Long id);
}
