package com.pbl5.service;

import com.pbl5.dtos.Pagination.ReceiptPaginationDTO;
import com.pbl5.dtos.ReceiptDTO;
import com.pbl5.helpers.respone.Message;

public interface IReceiptService {
    Message createReceipt(ReceiptDTO receiptDTO);
    // find all receipt admin
    Message findAllReceiptPagination(ReceiptPaginationDTO dto);
}
