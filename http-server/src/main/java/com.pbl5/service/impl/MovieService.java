package com.pbl5.service.impl;

import com.pbl5.dao.IMovieDAO;
import com.pbl5.dao.impl.MovieDAO;
import com.pbl5.dtos.MovieDTO;
import com.pbl5.dtos.Pagination.MoviePaginationDTO;
import com.pbl5.helpers.Http;
import com.pbl5.helpers.IDGeneration;
import com.pbl5.helpers.SaveFile;
import com.pbl5.helpers.respone.*;
import com.pbl5.models.Movie;
import com.pbl5.models.User;
import com.pbl5.service.IMovieService;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class MovieService implements IMovieService {
    private final IMovieDAO iMovieDAO = new MovieDAO();

    @Override
    public Message findAllMovieIsShowing() {



        List<Movie> results = iMovieDAO.findAllMovieIsShowing();

        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
        Data data = new Data.Builder(null).withResults(results).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message findAllMovieIsShowing(MoviePaginationDTO dto) {
        MoviePaginationDTO domain = Http.objectMapper(dto, MoviePaginationDTO.class);
        List<Movie> results = iMovieDAO.findAllMovieIsShowing(domain);
        Integer pages = iMovieDAO.countAllMovie();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(results).build();
        Pagination pagination = new Pagination.Builder().withCurrentPage(domain.getPage())
                .withTotalPages((int) Math.ceil((double) pages / PER_PAGE))
                .withTotalResults(pages).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
    }

    @Override
    public Message createMovie(Movie movie, String username) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(now);
        movie.setCreatedAt(timestamp);
        String id = IDGeneration.generate();
        movie.setId(id);
        try {
            iMovieDAO.createMovie(movie, username);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();

        } catch (Exception e) {

            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage(Response.CREATE_FAILED).build();
            return new Message.Builder(meta).build();

        }

    }

    @Override
    public Message deleteMovie(String movieId) {
        try {
            iMovieDAO.updateStatusMovie(movieId);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {

            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage(Response.FAILED).build();
            return new Message.Builder(meta).build();
        }

    }

    @Override
    public Message updateMovie(Movie movie, String username) {


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(now);
        movie.setModifiedAt(timestamp);
        try {
            iMovieDAO.updateMovie(movie, username);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage(Response.FAILED).build();
            return new Message.Builder(meta).build();
        }


    }

    @Override
    public Message findOneById(String movieId) {
        try {
            Movie movie = iMovieDAO.findOneById(movieId);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(movie).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withMessage("Movie Not Found").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message findImageOfMovieIsShowing() {
        try {
            List<Movie> moviesImages = iMovieDAO.findImageOfAllMovieIsShowing();
            List<Map<String, String>> results = new ArrayList<>();
            for (Movie moviesImage : moviesImages) {
                Map<String, String> paths = new HashMap<>();
                paths.put("id", moviesImage.getId());
                paths.put("path", moviesImage.getMoviePoster());
                results.add(paths);
            }
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(results).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message findAllMovieShowing() {
        try {
            List<Movie> movies = iMovieDAO.findAllMovieShowing();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
            Data data = new Data.Builder(null).withResults(movies).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withMessage("Movie Not Found").build();
            return new Message.Builder(meta).build();
        }
    }
}
