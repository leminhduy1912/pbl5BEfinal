package com.pbl5.dao.impl;

import com.pbl5.dao.IBookingDAO;
import com.pbl5.dtos.BookingDTO;
import com.pbl5.dtos.Pagination.BookingPaginationDTO;
import com.pbl5.helpers.mapper.BookingMapper;
import com.pbl5.helpers.mapper.CountMapper;
import com.pbl5.helpers.mapper.GetSeatAndStatusMapper;
import com.pbl5.models.Booking;

import java.util.List;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class BookingDAO extends AbstractDAO<Booking> implements IBookingDAO {
    @Override
    public void createBooking(Booking booking) {
        System.out.println("dang dat ghe");
        String sql="INSERT INTO bookings(bookingId,userId,showtimeId" +
                ",totalAmount,bookingDate,seatId,status) VALUES (?,?,?,?,?,?,?)";
             insert(sql, booking.getId(), booking.getUserId(), booking.getShowTimeId(),
                    booking.getTotalAmount(), booking.getBookingDate(), booking.getSeatId(),booking.getStatus());

    }
    // checkStatusSeatByShowTimeId
    @Override
    public List<Booking> findSeatAndStatusByShowTimeId(String showtimeId) {
        String sql="SELECT s.seatNum,b.status FROM bookings as b " +
                "INNER JOIN seats  as s on b.seatId = s.seatId " +
                "WHERE showtimeId=?";
        return query(sql,new GetSeatAndStatusMapper(),showtimeId);
    }

    @Override
    public Booking checkStatusSeatByShowTimeId(Booking booking) {
        String sql="SELECT  seatId,status from bookings WHERE showtimeId=? AND seatId=?";
        List<Booking> results = query(sql,new GetSeatAndStatusMapper(),
                booking.getShowTimeId(),booking.getSeatId());
        return results.isEmpty() ? null: results.get(0);
    }

    @Override
    public void changeStatus(String bookingId, String status) {
        String sql = "UPDATE bookings SET status =? WHERE bookingId =? ";
        update(sql,status,bookingId);
    }

    @Override
    public void deleteBooking(String id) {
        String sql = "DELETE FROM bookings WHERE bookingId = ?";
        update(sql,id);
    }

    @Override
    public Booking findOne(String id) {
        String sql = "SELECT * FROM bookings WHERE bookingId = ?";
        List<Booking> result = query(sql,new BookingMapper(),id);
        return result.isEmpty()? null: result.get(0);
    }

    @Override
    public List<Booking> findAllBookingFromUser(BookingPaginationDTO booking) {
        String sql = "SELECT b.*,m.title AS movieName,s.seatNum ,s.seatNum AS seatName,sh.startTime," +
                "sh.endTime FROM bookings AS b INNER JOIN seats AS s ON b.seatId=s.seatId " +
                "INNER JOIN showtimes AS sh ON sh.showtimeId=b.showtimeId " +
                "INNER JOIN movies AS m ON m.movie_id=sh.movieId " +
                "WHERE b.userId = ? "+" ORDER BY b.createdAt DESC  LIMIT "
                + PER_PAGE+" OFFSET " + (booking.getPage() - 1) * PER_PAGE;;
     return   query(sql,new BookingMapper(),booking.getUserId());


    }

    @Override
    public Integer countAllBooking() {
        String sql = "SELECT COUNT(*) as total FROM bookings ";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }
}
