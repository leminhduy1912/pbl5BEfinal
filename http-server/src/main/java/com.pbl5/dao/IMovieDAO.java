package com.pbl5.dao;

import com.pbl5.dtos.Pagination.MoviePaginationDTO;
import com.pbl5.models.Movie;

import java.util.List;

public interface IMovieDAO {
    // movie management by admin

    List<Movie> findAllMovieIsShowing();

    void createMovie(Movie movie,String username);
    void updateStatusMovie(String movieId);
    void updateMovie(Movie movie,String username);
    Movie findOneById(String movieId);
    List<Movie> findAllMovieIsShowing(MoviePaginationDTO pagination);
    List<Movie> findImageOfAllMovieIsShowing();
    Integer countAllMovie();
    List<Movie> findAllMovieShowing();
}
