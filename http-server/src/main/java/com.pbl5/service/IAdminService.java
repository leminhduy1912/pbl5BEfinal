package com.pbl5.service;

import com.pbl5.helpers.respone.Message;

public interface IAdminService {
    Message findTransactionByMonthAndYear(int month, int year);
    Message findUserByMonthAndYear(int month,int year);
    Message findBookingByMonthAndYear(int month,int year);
}
