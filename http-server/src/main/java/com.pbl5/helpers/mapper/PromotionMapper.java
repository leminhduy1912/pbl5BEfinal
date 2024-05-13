package com.pbl5.helpers.mapper;

import com.pbl5.models.Promotion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionMapper implements IMapper<Promotion>{
    @Override
    public Promotion mapRow(ResultSet result) throws SQLException {
        Promotion promotion = new Promotion();
        promotion.setDescription(result.getString("description"));
        promotion.setClause(result.getString("clause"));
        promotion.setImage(result.getString("image"));
        promotion.setTitle(result.getString("title"));
        promotion.setId(result.getString("promotion_id"));
        return promotion;
    }
}
