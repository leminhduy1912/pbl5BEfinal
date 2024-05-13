package com.pbl5.service.impl;

import com.pbl5.dao.ISeatDAO;
import com.pbl5.dao.impl.SeatDAO;
import com.pbl5.helpers.respone.Data;
import com.pbl5.helpers.respone.Message;
import com.pbl5.helpers.respone.Meta;
import com.pbl5.helpers.respone.Response;
import com.pbl5.models.Promotion;
import com.pbl5.models.Seat;
import com.pbl5.service.ISeatService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SeatService implements ISeatService {
    private ISeatDAO seatDAO = new SeatDAO();
    @Override
    public Message findSeatIdBySeatNameAndTheaterId(String seatname, String theaterId) {
//        Seat seat = seatDAO.findSeatIdBySeatNameAndTheaterId(seatname,theaterId);
//        if (seat)
        return null;
    }

    @Override
    public Message findAllSeatByTheaterId(String theaterId) {
        try {
            List<Seat> result = seatDAO.findAllSeatByTheaterId(theaterId);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(result).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }
}
