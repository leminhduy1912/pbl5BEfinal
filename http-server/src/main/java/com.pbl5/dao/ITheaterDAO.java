package com.pbl5.dao;

import com.pbl5.dtos.Pagination.TheaterPaginationDTO;
import com.pbl5.models.Theater;

import java.util.List;

public interface ITheaterDAO {
    List<Theater> findAllRoomWithPagination(TheaterPaginationDTO dto);
    Integer countAllRoom();
    void changeStatus(String id,String status);
}
