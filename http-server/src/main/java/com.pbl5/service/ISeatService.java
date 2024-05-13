package com.pbl5.service;

import com.pbl5.helpers.respone.Message;

public interface ISeatService {
    Message findSeatIdBySeatNameAndTheaterId(String seatname, String theaterId);
    Message findAllSeatByTheaterId(String theaterId);
}
