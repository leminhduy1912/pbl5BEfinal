package com.pbl5.helpers.mapper;

import com.pbl5.dtos.Pagination.TheaterPaginationDTO;
import com.pbl5.models.Theater;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TheaterMapper  implements IMapper<Theater>{
    @Override
    public Theater mapRow(ResultSet result) throws SQLException {
        Theater theater = new Theater();
        theater.setId(result.getString("theaterId"));
        theater.setName(result.getString("theaterName"));
        theater.setCapacity(result.getInt("capacity"));
        theater.setStatus(result.getString("status"));
        theater.setCreatedAt(result.getTimestamp("createdAt"));
        return theater;
    }
}
