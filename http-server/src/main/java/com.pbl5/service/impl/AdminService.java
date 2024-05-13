package com.pbl5.service.impl;

import com.pbl5.dao.IAdminDAO;
import com.pbl5.dao.impl.AdminDAO;
import com.pbl5.helpers.respone.Data;
import com.pbl5.helpers.respone.Message;
import com.pbl5.helpers.respone.Meta;
import com.pbl5.helpers.respone.Response;
import com.pbl5.service.IAdminService;

import javax.servlet.http.HttpServletResponse;

public class AdminService implements IAdminService {
    private IAdminDAO adminDAO = new AdminDAO();
    @Override
    public Message findTransactionByMonthAndYear(int month, int year) {
        try {
            Integer quantity = adminDAO.findBookingByMonthAndYear(month, year);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(quantity).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withMessage("Error").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message findUserByMonthAndYear(int month, int year) {
        try {
            Integer quantity = adminDAO.findUserByMonthAndYear(month, year);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(quantity).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withMessage("Error").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message findBookingByMonthAndYear(int month, int year) {
        try {
            Integer quantity = adminDAO.findBookingByMonthAndYear(month, year);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(quantity).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withMessage("Error").build();
            return new Message.Builder(meta).build();
        }
    }
}
