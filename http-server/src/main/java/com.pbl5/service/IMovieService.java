package com.pbl5.service;

import com.pbl5.dtos.Pagination.MoviePaginationDTO;
import com.pbl5.helpers.respone.Message;
import com.pbl5.models.Movie;

public interface IMovieService {
    Message findAllMovieIsShowing();
    Message findAllMovieIsShowing(MoviePaginationDTO dto);

    Message createMovie(Movie movie,String username);
    Message deleteMovie(String movieId);
    Message updateMovie(Movie movie,String username);
    Message findOneById(String movieId);
    Message findImageOfMovieIsShowing();
    Message findAllMovieShowing();
}
