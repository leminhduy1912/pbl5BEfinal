package com.pbl5.service.impl;

import com.pbl5.dao.IReceiptDAO;
import com.pbl5.dao.impl.ReceiptDAO;
import com.pbl5.dtos.Pagination.MoviePaginationDTO;
import com.pbl5.dtos.Pagination.ReceiptPaginationDTO;
import com.pbl5.dtos.ReceiptDTO;
import com.pbl5.helpers.Http;
import com.pbl5.helpers.IDGeneration;
import com.pbl5.helpers.respone.*;
import com.pbl5.models.Movie;
import com.pbl5.service.IReceiptService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class ReceiptService implements IReceiptService {
    private IReceiptDAO receiptDAO = new ReceiptDAO();
    @Override
    public Message createReceipt(ReceiptDTO receiptDTO) {
        String id = IDGeneration.generate();
        receiptDTO.setId(id);
        try {
            receiptDAO.createReceipt(receiptDTO);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();

        } catch (Exception e) {

            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage(Response.CREATE_FAILED).build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message findAllReceiptPagination(ReceiptPaginationDTO dto) {
     ReceiptPaginationDTO domain = Http.objectMapper(dto, ReceiptPaginationDTO.class);
       List<ReceiptDTO> results = receiptDAO.findAllReceiptWithPagination(domain);
        Integer pages = receiptDAO.countAllReceipt();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(results).build();
        Pagination pagination = new Pagination.Builder().withCurrentPage(domain.getPage())
                .withTotalPages((int) Math.ceil((double) pages / PER_PAGE))
                .withTotalResults(pages).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
    }
}
