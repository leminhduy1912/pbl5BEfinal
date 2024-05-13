package com.pbl5.dao.impl;

import com.pbl5.dao.IReceiptDAO;
import com.pbl5.dtos.Pagination.ReceiptPaginationDTO;
import com.pbl5.dtos.ReceiptDTO;
import com.pbl5.helpers.mapper.CountMapper;
import com.pbl5.helpers.mapper.ReceiptMapper;

import java.util.List;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class ReceiptDAO extends AbstractDAO<ReceiptDTO> implements IReceiptDAO {
    @Override
    public void createReceipt(ReceiptDTO receiptDTO) {
        String sql="INSERT INTO receipts (id,content,user_id,amount)" +
                "VALUES (?,?,?,?)";
        insert(sql,receiptDTO.getId(),receiptDTO.getContent(),receiptDTO.getUserId()
                ,receiptDTO.getAmount());
    }

    @Override
    public List<ReceiptDTO> findAllReceiptWithPagination(ReceiptPaginationDTO dto) {

        String sql="SELECT r.*,u.user_id,u.email FROM pbl5.receipts as r INNER JOIN users as u on r.user_id= u.user_id ORDER BY create_at DESC LIMIT "
                + PER_PAGE+" OFFSET "+ (dto.getPage() - 1) * PER_PAGE;
        return query(sql, new ReceiptMapper());

    }
    @Override
    public Integer countAllReceipt() {
        String sql = "SELECT COUNT(*) as total FROM receipts ";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }


}
