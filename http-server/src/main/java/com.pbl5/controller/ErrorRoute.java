package com.pbl5.controller;

import com.pbl5.utils.exceptions.ErrorHandler;
import com.pbl5.utils.exceptions.apiException.InvalidEndpointException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/*"})
public class ErrorRoute extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, new InvalidEndpointException());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, new InvalidEndpointException());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, new InvalidEndpointException());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, new InvalidEndpointException());
    }

}
