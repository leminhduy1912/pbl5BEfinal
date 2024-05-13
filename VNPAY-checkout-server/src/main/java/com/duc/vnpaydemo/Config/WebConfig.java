//package com.duc.vnpaydemo.Config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//
////    @Override
////    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
////                                    final FilterChain filterChain) throws ServletException, IOException {
//////        response.addHeader("Access-Control-Allow-Origin", "*");
////        response.addHeader("Access-Control-Allow-Origin", "http://localhost:5173");
////
////        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
////        response.addHeader("Access-Control-Allow-Headers", "*");
////        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
////        response.addHeader("Access-Control-Allow-Credentials", "true");
////        response.addIntHeader("Access-Control-Max-Age", 10);
////        filterChain.doFilter(request, response);
////    }
//@Component
//public class WebConfig extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
//                                    final FilterChain filterChain) throws ServletException, IOException {
//        response.addHeader("Access-Control-Allow-Origin", "http://localhost:5173");
//
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
//        response.addHeader("Access-Control-Allow-Headers", "*");
//        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
////        response.addHeader("Access-Control-Allow-Credentials", "true");
//        response.addIntHeader("Access-Control-Max-Age", 10);
//        filterChain.doFilter(request, response);
//    }
//}
//
