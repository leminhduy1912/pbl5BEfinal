package com.pbl5.dao;

public interface IAdminDAO {
    Integer findTransactionByMonthAndYear(int month,int year);
    Integer findUserByMonthAndYear(int month,int year);
    Integer findBookingByMonthAndYear(int month,int year);
}
