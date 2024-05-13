package com.pbl5.helpers.mapper;

import com.pbl5.dtos.BookingDTO;
import com.pbl5.models.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingMapper implements IMapper<Booking>{
    @Override
    public Booking mapRow(ResultSet result) throws SQLException {
        Booking booking = new Booking();

        booking.setShowTimeId(result.getString("showtimeId"));
        booking.setUserId(result.getString("userId"));
        booking.setSeatId(result.getString("seatId"));
        System.out.println(result.getString("seatNum"));

        try {
            booking.setSeatName(result.getString("seatNum"));

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {

            booking.setSeatName(result.getString("seatName"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {

            booking.setStartTime(result.getString("startTime"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {

            booking.setEndTime(result.getString("endTime"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        booking.setStatus(result.getString("status"));

        booking.setTotalAmount(result.getInt("totalAmount"));
        booking.setBookingDate(result.getString("bookingDate"));
        booking.setId(result.getString("bookingId"));
        booking.setMovieName(result.getString("movieName"));

        return booking;
    }
}
