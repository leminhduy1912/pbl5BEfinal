package com.pbl5.controller.Movie;


import com.pbl5.helpers.Http;
import com.pbl5.models.Movie;
import com.pbl5.service.IMovieService;
import com.pbl5.service.impl.MovieService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.pbl5.utils.constants.Endpoint.V1;

@WebServlet(urlPatterns = {V1 + "/movie"})
public class FindOneByIdController extends HttpServlet {
    private IMovieService movieService = new MovieService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Movie dto = Http.paramsToString(req.getParameterMap()).toModel(Movie.class);
        ErrorHandler.handle(resp, () -> movieService.findOneById(dto.getId()));
    }
}
