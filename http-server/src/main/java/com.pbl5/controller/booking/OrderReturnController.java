package com.pbl5.controller.booking;

import com.pbl5.configs.VNPayService;
import com.pbl5.helpers.Http;
import com.pbl5.models.Booking;
import com.pbl5.service.IBookingService;
import com.pbl5.service.impl.BookingService;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


//@WebServlet(urlPatterns = {"/vnpay-payment"})
//@MultipartConfig
//public class OrderReturnController extends HttpServlet {
//    private VNPayService vnPayService = new VNPayService();
//    private IBookingService bookingService = new BookingService();
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int paymentStatus =vnPayService.orderReturn(req);
//        String orderInfo = req.getParameter("vnp_OrderInfo");
//        String paymentTime = req.getParameter("vnp_PayDate");
//        String transactionId = req.getParameter("vnp_TransactionNo");
//        String totalPrice = req.getParameter("vnp_Amount");
//
//
//        ErrorHandler.handle(resp, () -> bookingService.orderReturn(orderInfo,paymentTime,transactionId,totalPrice,paymentStatus));
//        // Tạo mã JavaScript để mở tab mới và chuyển hướng đến URL chỉ định
//        resp.sendRedirect("https://www.youtube.com/");
//    }
//}


//@WebServlet(urlPatterns = {"/vnpay-payment"})
@WebServlet("/vnpay-payment")
public class OrderReturnController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hhhh");
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
