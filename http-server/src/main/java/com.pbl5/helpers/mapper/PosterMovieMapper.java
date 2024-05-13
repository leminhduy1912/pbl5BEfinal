package com.pbl5.helpers.mapper;

import com.pbl5.helpers.TimestampConvert;
import com.pbl5.models.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PosterMovieMapper implements IMapper<Movie>{
    @Override
    public Movie mapRow(ResultSet result) {
        Movie movie = new Movie();
        try {
            movie.setId(result.getString("movie_id"));
            movie.setMoviePoster(result.getString("poster"));
            return movie;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return null;
        }
    }
}
