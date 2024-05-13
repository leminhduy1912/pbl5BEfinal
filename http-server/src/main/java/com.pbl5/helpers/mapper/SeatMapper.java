package com.pbl5.helpers.mapper;

import com.pbl5.models.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatMapper implements IMapper<Seat>{

    @Override
    public Seat mapRow(ResultSet result) throws SQLException {
        Seat seat = new Seat();
        seat.setSeatId(result.getString("seatId"));
        seat.setTheaterId(result.getString("theaterId"));
        seat.setSeatNum(result.getString("seatNum"));
        seat.setPrice(result.getInt("price"));
        return seat;
    }
}
