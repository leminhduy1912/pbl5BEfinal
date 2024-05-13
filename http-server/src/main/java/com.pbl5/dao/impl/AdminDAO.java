package com.pbl5.dao.impl;

import com.pbl5.dao.IAdminDAO;
import com.pbl5.dtos.ReceiptDTO;
import com.pbl5.helpers.mapper.CountMapper;

import java.util.List;

public class AdminDAO extends AbstractDAO<ReceiptDTO> implements IAdminDAO {
    @Override
    public Integer findTransactionByMonthAndYear(int month, int year) {
        String sql="SELECT COUNT(*) AS total FROM pbl5.receipts WHERE YEAR(create_at)=? " +
                "AND MONTH(create_at)=? GROUP BY YEAR(create_at), MONTH(create_at)";
        List<Integer> pages = query(sql, new CountMapper(),year,month);
        return pages.isEmpty() ? null : pages.get(0);
    }

    @Override
    public Integer findUserByMonthAndYear(int month, int year) {
        String sql="SELECT COUNT(*) AS total FROM pbl5.users WHERE YEAR(createdAt)=? " +
                "AND MONTH(createdAt)=? GROUP BY YEAR(createdAt), MONTH(createdAt)";
        List<Integer> pages = query(sql, new CountMapper(),year,month);
        return pages.isEmpty() ? null : pages.get(0);
    }

    @Override
    public Integer findBookingByMonthAndYear(int month, int year) {
        String sql="SELECT COUNT(*) AS total FROM pbl5.bookings WHERE YEAR(createdAt)=? " +
                "AND MONTH(createdAt)=? GROUP BY YEAR(createdAt), MONTH(createdAt)";
        List<Integer> pages = query(sql, new CountMapper(),year,month);
        return pages.isEmpty() ? null : pages.get(0);
    }
}
