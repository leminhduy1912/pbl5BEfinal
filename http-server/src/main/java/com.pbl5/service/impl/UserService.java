package com.pbl5.service.impl;

import com.pbl5.dao.IUserDAO;
import com.pbl5.dao.impl.UserDAO;
import com.pbl5.dtos.Pagination.MoviePaginationDTO;
import com.pbl5.dtos.Pagination.UserPaginationDTO;
import com.pbl5.helpers.BcryptPassword;
import com.pbl5.helpers.Http;
import com.pbl5.helpers.IDGeneration;
import com.pbl5.helpers.respone.*;
import com.pbl5.models.Movie;
import com.pbl5.models.User;
import com.pbl5.service.IUserService;
import com.pbl5.utils.SendEmail;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class UserService implements IUserService {
    private IUserDAO userDAO = new UserDAO();
    // manage all user for admin
    @Override
    public Message findAllUserPagination(UserPaginationDTO dto) {
        UserPaginationDTO domain = Http.objectMapper(dto, UserPaginationDTO.class);
        List<User> results = userDAO.findAllUserPagination(domain);
        Integer pages = userDAO.countAllUser();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(results).build();
        Pagination pagination = new Pagination.Builder().withCurrentPage(domain.getPage())
                .withTotalPages((int) Math.ceil((double) pages / PER_PAGE))
                .withTotalResults(pages).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
    }

    @Override
    public Message updateUser(User user) {
        // admin update user
        try {
//            String id = IDGeneration.generate();
//            user.setId(id);
            userDAO.update(user);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            return new Message.Builder(meta).build();

        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }

    }
    //reser password
    @Override
    public Message resetPassword(User user) {
        // admin reset password user
        try {
            SendEmail sm = new SendEmail();
            String randomString = generateRandomString(6);
            System.out.println("email to : "+user.getEmail());
            boolean test = sm.sendEmail(user.getEmail(), "PBL5 Movie ticket online booking system: Reset Password","Your password to reset  : "+randomString);
            if (test) {
                userDAO.resetPassword(BcryptPassword.HashPW(randomString), user.getId());
                Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
                return new Message.Builder(meta).build();

            } else {
                Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_ACCEPTABLE).withMessage(Response.FAILED).build();
                return new Message.Builder(meta).build();
            }
        } catch (Exception e){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage(Response.FAILED).build();
            return new Message.Builder(meta).build();
        }

    }

    @Override
    public Message userResetPassword(User user) {
        try {
            User user1= userDAO.findByEmail(user.getEmail(),2);
            if (user1 == null){
                Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withMessage(Response.FAILED).build();
                return new Message.Builder(meta).build();
            } else {
                SendEmail sm = new SendEmail();
                String randomString = generateRandomString(6);
                System.out.println("email to : "+user.getEmail());
                boolean test = sm.sendEmail(user.getEmail(), "PBL5 Movie ticket online booking system: Reset Password"
                        ,"Your password to reset  : "+randomString);
                if (test) {
                    userDAO.userResetPassword(BcryptPassword.HashPW(randomString), user.getEmail());
                    Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
                    return new Message.Builder(meta).build();

                } else {
                    Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_ACCEPTABLE).withMessage(Response.FAILED).build();
                    return new Message.Builder(meta).build();
                }
            }
        } catch (Exception e){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage(Response.FAILED).build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message findByEmail(User user) {
        try {

            User user1=userDAO.findByEmail(user.getEmail(), user.getRoleId());
            Data data = new Data.Builder(null).withResults(user1).build();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            return new Message.Builder(meta).withData(data).build();

        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message updatePoint(User user) {
        try {
           userDAO.updatePoint(user);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            return new Message.Builder(meta).build();

        } catch (Exception e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withMessage("Internal Server Error").build();
            return new Message.Builder(meta).build();
        }
    }

    private  String generateRandomString(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}
