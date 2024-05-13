package com.pbl5.dtos;

import java.util.List;

public class BookingDTO {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String userId;
    private String seat;
    private String payment;
    private String showTimeId;
    private int totalAmount;

    public Long getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Long bookingDate) {
        this.bookingDate = bookingDate;
    }

    private Long bookingDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(String showTimeId) {
        this.showTimeId = showTimeId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
