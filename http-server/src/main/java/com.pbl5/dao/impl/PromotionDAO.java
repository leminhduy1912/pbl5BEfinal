package com.pbl5.dao.impl;

import com.pbl5.dao.IPromotionDAO;
import com.pbl5.helpers.mapper.ImagePromotionMapper;
import com.pbl5.helpers.mapper.PromotionMapper;
import com.pbl5.models.Promotion;

import java.util.List;
import java.util.logging.Logger;

public class PromotionDAO extends AbstractDAO<Promotion> implements IPromotionDAO   {
    private static final Logger logger = Logger.getLogger("Promotion DAO");
    @Override
    public List<Promotion> findAll() {
        logger.info("Find all promotion ");
        String sql="SELECT * FROM promotions";
        return query(sql,new PromotionMapper());
    }

    @Override
    public Promotion findOneById(String id) {
        String sql = "SELECT * FROM promotions WHERE promotion_id = ?";
        List<Promotion> result = query(sql,new PromotionMapper(),id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Promotion> findAllImage() {
        String sql="SELECT  image,promotion_id FROM promotions";
        return query(sql,new ImagePromotionMapper());
    }

    @Override
    public void createPromotion(Promotion promotion,String username) {
        logger.info("Create promotion ");
        String sql="INSERT INTO promotions (promotion_id,title,description,clause,createdBy,createdAt,image)" +
               "VALUES (?,?,?,?,?,?,?)";
        insert(sql,promotion.getId(),promotion.getTitle(),promotion.getDescription()
                ,promotion.getClause(),username,promotion.getCreatedAt(),promotion.getImage());
    }

    @Override
    public void updatePromotion(Promotion promotion) {

    }

    @Override
    public void deletePromotion(Long id) {

    }
}
