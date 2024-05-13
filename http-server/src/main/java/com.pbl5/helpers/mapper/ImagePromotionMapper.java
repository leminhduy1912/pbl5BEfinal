package com.pbl5.helpers.mapper;

import com.pbl5.models.Promotion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImagePromotionMapper implements IMapper<Promotion>{
    @Override
    public Promotion mapRow(ResultSet result) throws SQLException {
        Promotion promotion= new Promotion();
        promotion.setImage(result.getString("image"));
        promotion.setId(result.getString("promotion_id"));
        return promotion;
    }
}
