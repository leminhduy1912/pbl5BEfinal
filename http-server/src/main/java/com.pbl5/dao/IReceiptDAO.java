package com.pbl5.dao;

import com.pbl5.dtos.Pagination.ReceiptPaginationDTO;
import com.pbl5.dtos.ReceiptDTO;

import java.util.List;

public interface IReceiptDAO {
    void createReceipt(ReceiptDTO receiptDTO);




    // find all receipt admin
    List<ReceiptDTO> findAllReceiptWithPagination(ReceiptPaginationDTO dto);
    Integer countAllReceipt();
}
