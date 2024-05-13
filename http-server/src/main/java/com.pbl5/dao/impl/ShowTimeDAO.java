package com.pbl5.dao.impl;


import com.pbl5.dao.IShowTimeDAO;
import com.pbl5.dtos.Pagination.ShowTimePaginationDTO;
import com.pbl5.helpers.mapper.CountMapper;
import com.pbl5.helpers.mapper.ShowTimeMapper;
import com.pbl5.models.ShowTime;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;
import java.util.List;

public class ShowTimeDAO extends AbstractDAO<ShowTime> implements IShowTimeDAO {
    @Override
    public List<ShowTime> getShowTimeByMovieIdAndDate(String movieId, String dateShow) {

        String sql = "SELECT s.* FROM showtimes AS s INNER JOIN theaters as t on s.theaterId=t.theaterId WHERE s.movieId = ? AND s.date_show = ? AND t.status=?";
        return query(sql, new ShowTimeMapper(), movieId, dateShow,1);

    }

    @Override
    public void createShowTime(ShowTime showTime,String username) {

        String sql="INSERT INTO showtimes(showtimeId,movieId,theaterId,startTime,endTime,date_show,status,createdAt,createdBy) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        insert(sql,showTime.getId(),showTime.getMovieId(),showTime.getTheaterId(),showTime.getTimeStart()
                ,showTime.getTimeEnd(),showTime.getDateShow(),1,showTime.getCreatedAt(),username);
    }

    @Override
    public List<ShowTime> findByMovieIdAndDateShow(String movieId, String dateShow) {

        String[] parts = dateShow.split("-");
        String sql ="SELECT * FROM showtimes AS s INNER JOIN theaters AS t on s.theaterId = t.theaterId INNER JOIN movies as m on m.movie_id=s.movieId" +
                " WHERE s.movieId = ? AND t.status = 1 " +
                "AND SUBSTRING_INDEX(s.date_show, '-', 1) = ? " +
                "AND SUBSTRING_INDEX(SUBSTRING_INDEX(s.date_show, '-', 2), '-', -1) = ? " +
                "AND SUBSTRING_INDEX(s.date_show, '-', -1) =?" +
                "ORDER BY SUBSTRING_INDEX(s.startTime, ':', 1) ASC";
        return query(sql,new ShowTimeMapper(),movieId,parts[0],parts[1],parts[2]);
    }

    @Override
    public List<ShowTime> findAllAPagination(ShowTimePaginationDTO dto) {
        String sql="SELECT s.*,m.title,t.theaterName FROM showtimes AS s INNER JOIN movies AS m on s.movieId = m.movie_id " +
                "INNER JOIN theaters AS t on s.theaterId = t.theaterId  ORDER BY s.createdAt DESC  LIMIT "
                + PER_PAGE+ " OFFSET " + (dto.getPage() - 1) * PER_PAGE;
        return query(sql,new ShowTimeMapper());
    }

    @Override
    public void update(ShowTime dto) {
        String sql="UPDATE showtimes SET movieId=?,theaterId=?,startTime=?,endTime=?,date_show=?,status=? WHERE showtimeId=?";
        update(sql,dto.getMovieId(),dto.getTheaterId(),dto.getTimeStart(),dto.getTimeEnd(),dto.getDateShow(),dto.getStatus(),dto.getId());
    }

    @Override
    public List<ShowTime> findByDateShowAndMovieNameAndStatus(ShowTime dto) {
        String sql="SELECT s.*,m.title,t.theaterName from showtimes AS s  INNER JOIN movies AS m on m.movie_id = s.movieId INNER JOIN theaters AS t on t.theaterId = s.theaterId WHERE s.status= ? AND m.title= ? AND s.date_show= ? ";
        return query(sql,new ShowTimeMapper(), dto.getStatus(),dto.getMovieName(),dto.getDateShow());
    }

    @Override
    public Integer countAllShowtime() {
        String sql = "SELECT COUNT(*) as total FROM showtimes ";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }
}
