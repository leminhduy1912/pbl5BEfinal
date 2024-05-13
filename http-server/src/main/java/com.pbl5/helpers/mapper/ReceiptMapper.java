package com.pbl5.helpers.mapper;

import com.pbl5.dtos.ReceiptDTO;
import com.pbl5.models.Promotion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptMapper implements IMapper<ReceiptDTO>{
    @Override
    public ReceiptDTO mapRow(ResultSet result) throws SQLException {
        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setId(result.getString("id"));
        receiptDTO.setContent(result.getString("content"));
        receiptDTO.setUserId(result.getString("user_id"));
        receiptDTO.setAmount(result.getString("amount"));
        receiptDTO.setCreateDate(result.getTimestamp("create_at"));
        receiptDTO.setEmail(result.getString("email"));
        return receiptDTO;
    }
}
