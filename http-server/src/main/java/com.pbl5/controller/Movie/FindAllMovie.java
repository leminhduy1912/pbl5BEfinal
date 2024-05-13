package com.pbl5.controller.Movie;

import com.pbl5.dao.IMovieDAO;
import com.pbl5.dao.impl.MovieDAO;
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

import static com.pbl5.utils.constants.Endpoint.ADMIN;
import static com.pbl5.utils.constants.Endpoint.V1;

@MultipartConfig
@WebServlet(urlPatterns = {V1  + "/movie/all"})
public class FindAllMovie extends HttpServlet {
    private IMovieService movieService = new MovieService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ErrorHandler.handle(resp, () -> movieService.findAllMovieIsShowing());
    }
}
