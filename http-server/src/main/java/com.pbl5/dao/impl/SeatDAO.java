package com.pbl5.dao.impl;

import com.pbl5.dao.ISeatDAO;
import com.pbl5.helpers.mapper.SeatMapper;
import com.pbl5.models.Seat;

import java.util.List;

public class SeatDAO extends AbstractDAO<Seat> implements ISeatDAO {
    @Override
    public Seat findSeatIdBySeatNameAndTheaterId(String seatName, String theaterId) {
        String sql="SELECT * FROM seats WHERE seatNum=? AND theaterId = ?";
        List<Seat> seats = query(sql,new SeatMapper(),seatName,theaterId);
        return seats.isEmpty() ? null :seats.get(0);
    }

    @Override
    public List<Seat> findAllSeatByTheaterId(String theaterId) {
        String sql="SELECT * FROM seats where theaterId=?";
        return query(sql,new SeatMapper(),theaterId);
    }
}
