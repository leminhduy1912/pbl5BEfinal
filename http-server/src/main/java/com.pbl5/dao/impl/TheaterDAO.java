package com.pbl5.dao.impl;

import com.pbl5.dao.ITheaterDAO;
import com.pbl5.dtos.Pagination.TheaterPaginationDTO;
import com.pbl5.helpers.mapper.CountMapper;
import com.pbl5.helpers.mapper.TheaterMapper;
import com.pbl5.models.Theater;

import java.util.List;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class TheaterDAO extends AbstractDAO<Theater> implements ITheaterDAO {
    @Override
    public List<Theater> findAllRoomWithPagination(TheaterPaginationDTO dto) {
        String sql="SELECT * FROM theaters  ORDER BY createdAt DESC LIMIT "+ PER_PAGE
                +" OFFSET "+ (dto.getPage() - 1) * PER_PAGE;
        return query(sql,new TheaterMapper());
    }

    @Override
    public Integer countAllRoom() {
        String sql = "SELECT COUNT(*) as total FROM theaters ";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }

    @Override
    public void changeStatus(String id, String status) {
        String sql = "UPDATE theaters SET status=? WHERE theaterId= ?";
        update(sql,status,id);
    }
}
