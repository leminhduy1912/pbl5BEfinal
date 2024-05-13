package com.pbl5.controller.Movie;

import com.pbl5.dao.IMovieDAO;
import com.pbl5.dao.impl.MovieDAO;
import com.pbl5.dtos.Pagination.MoviePaginationDTO;
import com.pbl5.dtos.UserDTO;
import com.pbl5.dtos.UserSignInDTO;
import com.pbl5.helpers.Http;
import com.pbl5.helpers.SaveFile;
import com.pbl5.models.Movie;
import com.pbl5.service.IMovieService;
import com.pbl5.service.impl.MovieService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.*;

@WebServlet(urlPatterns = {V1 + ADMIN + "/movie"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 11
)
public class MovieController extends HttpServlet {
    private IMovieService movieService = new MovieService();
    private IMovieDAO movieDAO = new MovieDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Movie movie = Http.paramsToString(req.getParameterMap()).toModel(Movie.class);
        System.out.println(movie.getTitle());
        String path = SaveFile.save(req, "image");
        movie.setMoviePoster(path);
        ErrorHandler.handle(resp, () -> movieService.createMovie(movie, req.getHeader("username")));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Movie movie = Http.paramsToString(req.getParameterMap()).toModel(Movie.class);
        ErrorHandler.handle(resp, () -> movieService.deleteMovie(movie.getId()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Movie movie = Http.paramsToString(req.getParameterMap()).toModel(Movie.class);
        Movie movieOld = movieDAO.findOneById(movie.getId());
        System.out.println("here");
        System.out.println("id movie :"+movie.getId());
        if (movieOld.getId() != null) {
            if (req.getPart("image") != null) {
                System.out.println("co gui anh");
                System.out.println("poster cu : "+movieOld.getMoviePoster());
                boolean isImageDelete = SaveFile.delete(movieOld.getMoviePoster());
                if (isImageDelete) {
                    String path = SaveFile.save(req, "image");
                    System.out.println("poster moi : "+path);

                    movie.setMoviePoster(path);
                }
            } else {
                movie.setMoviePoster(movieOld.getMoviePoster());
            }
            ErrorHandler.handle(resp, () -> movieService.updateMovie(movie, req.getHeader("username")));
        }
    }
}
