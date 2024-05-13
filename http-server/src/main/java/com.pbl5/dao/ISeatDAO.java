package com.pbl5.dao;

import com.pbl5.models.Seat;

import java.util.List;

public interface ISeatDAO {
    Seat findSeatIdBySeatNameAndTheaterId(String seatName,String theaterId);
    List<Seat> findAllSeatByTheaterId(String theaterId);
}
