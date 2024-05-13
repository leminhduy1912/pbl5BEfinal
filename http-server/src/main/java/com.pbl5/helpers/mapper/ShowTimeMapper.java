package com.pbl5.helpers.mapper;

import com.pbl5.models.ShowTime;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowTimeMapper implements IMapper<ShowTime>{
    @Override
    public ShowTime mapRow(ResultSet result) throws SQLException {
        ShowTime showTime = new ShowTime();
        showTime.setMovieId(result.getString("movieId"));
        showTime.setTheaterName(result.getString("theaterName"));
        showTime.setTheaterId(result.getString("theaterId"));
        showTime.setTimeEnd(result.getString("endTime"));
        showTime.setTimeStart(result.getString("startTime"));
        showTime.setDateShow(result.getString("date_show"));
        showTime.setId(result.getString("showtimeId"));
        showTime.setStatus(result.getInt("status"));
        showTime.setMovieName(result.getString("title"));
        return showTime;
    }
}
