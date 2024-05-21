package com.pbl5.service.impl;

import com.pbl5.dao.IUserDAO;
import com.pbl5.dao.impl.UserDAO;
import com.pbl5.dtos.UserDTO;
import com.pbl5.dtos.UserSignInDTO;
import com.pbl5.helpers.*;
import com.pbl5.helpers.respone.Data;
import com.pbl5.helpers.respone.Message;
import com.pbl5.helpers.respone.Meta;
import com.pbl5.helpers.respone.Response;
import com.pbl5.models.User;
import com.pbl5.service.IAuthService;
import com.pbl5.utils.SendEmail;
import com.pbl5.utils.exceptions.dbException.CreateFailedException;
import com.pbl5.utils.exceptions.dbException.DuplicateEntryException;
import com.pbl5.utils.exceptions.dbException.NotFoundException;
import com.pbl5.utils.exceptions.dbException.UnexpectedException;
import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.apache.hc.core5.http.NotImplementedException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class AuthService implements IAuthService {

    private static final IUserDAO iUserDAO = new UserDAO();

    @Override
    public Message Login(UserSignInDTO dto) throws UnexpectedException, NotFoundException, InvalidCredentialsException {
        //login
        if (dto.getEmail() == null || dto.getPassword() == null){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_BAD_REQUEST).withMessage(Response.INVALID_EMAIL_OR_PASSWORD).build();
            return new Message.Builder(meta).build();
        }
        JwtGeneration JWT;
        HashMap<String, String> claims;
        try {
            claims = new HashMap<>();
            JWT = new JwtGeneration();
        } catch (NoSuchAlgorithmException e) {
            throw new UnexpectedException();
        }
        User user = iUserDAO.findByEmail(dto.getEmail(),dto.getRoleId());
        if (user == null) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withMessage(Response.USER_NOT_FOUND).build();
            return new Message.Builder(meta).build();
        }
        String hashedPassword = user.getPassword();
        boolean checkPassword = DecryptPassword.Decrypt(dto.getPassword(),hashedPassword);
        if (!checkPassword){
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_ACCEPTABLE).withMessage(Response.WRONG_PASSWORD).build();
            return new Message.Builder(meta).build();
        }

        claims.put("userId", user.getId());
        claims.put("role", user.getRoleCode());

        String role = user.getRoleCode();
        String accessToken = JWT.generate(claims);

        Data data = new Data.Builder(accessToken).withRole(role).withEmail(user.getEmail()).withId(user.getId()).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.LOGIN_SUCCESS).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message Register(UserDTO dto) throws DuplicateEntryException, CreateFailedException, UnexpectedException, NotFoundException, InvalidCredentialsException {



        User isEmailExist = iUserDAO.findByEmail(dto.getEmail(),dto.getRoleId());
        //register
        if (isEmailExist != null)
            throw new DuplicateEntryException(Response.EMAIL_IN_USE);
        User domain = Http.objectMapper(dto, User.class);
        String id = IDGeneration.generate();
        domain.setPassword(BcryptPassword.HashPW(dto.getPassword()));
        domain.setId(id);
        domain.setCreatedBy(domain.getEmail());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(now);
        domain.setCreatedAt(timestamp);
        try {
           iUserDAO.save(domain);
            System.out.println("saving................................");
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.REGISTER_SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            if (e instanceof UnexpectedException)
                throw new UnexpectedException();
            if (e instanceof NotFoundException)
                throw new NotFoundException(Response.USER_NOT_FOUND);
            if (e instanceof InvalidCredentialsException)
                throw new InvalidCredentialsException(Response.WRONG_PASSWORD);
            else
                throw new CreateFailedException(Response.REGISTERED_FAILED);
        }
        
    }

    @Override
    public Message InspectorRegister(UserDTO dto, String userId) throws DuplicateEntryException, CreateFailedException, NotImplementedException {
        return null;
    }

    @Override
    public Message ResetPassword(String id, String userId) throws NotFoundException, UnexpectedException {
        return null;
    }

    @Override
    public Message checkEmailExistAndSendVerifyMailCode(String email,int roleid) {
        System.out.println("email nhan duoc "+email);
        User isEmailExist = iUserDAO.findByEmail(email,roleid);

        if (isEmailExist == null){
            System.out.println("khong co email");
            Map<String, String> result = new HashMap<>();
            result.put("email", email);
            SendEmail sm = new SendEmail();
            String code = sm.getRandom();
            result.put("verifyCode", code);


            User user = new User();
            user.setVertificationCode(code);
            user.setEmail(email);
            System.out.println("email send :" + user.getEmail() );
            boolean test = sm.sendEmail(user.getEmail(), "PBL5 Movie ticket online booking system: Register email confirmation","Your 4 digits code to confirmation email is : "+user.getVertificationCode());
            if (test) {
                Data data = new Data.Builder(null).withResults(result).build();
                Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
                return new Message.Builder(meta).withData(data).build();

            } else {
                Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_ACCEPTABLE).withMessage(Response.FAILED).build();
                return new Message.Builder(meta).build();
            }

        }  else {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CONFLICT).withMessage(Response.EMAIL_IN_USE).build();
            return new Message.Builder(meta).build();
        }
//        if (isEmailExist.is){
//            System.out.println("khong co email");
//            Map<String,String> result = new HashMap<>();
//            result.put("email", isEmailExist.getEmail());
//            SendEmail sm = new SendEmail();
//            String code = sm.getRandom();
//            result.put("verify-code",code);
//
//
//            User user = new User();
//            user.setVertificationCode(code);
//            user.setEmail(isEmailExist.getEmail());
//            System.out.println("email send :"+isEmailExist.getEmail());
//            boolean test = sm.sendEmail(user);
//            if(test){
//                Data data = new Data.Builder(null).withResults(result).build();
//                Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.SUCCESS).build();
//                return new Message.Builder(meta).withData(data).build();
//
//            }else{
//                Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_ACCEPTABLE).withMessage(Response.FAILED).build();
//                return new Message.Builder(meta).build();
//            }
//
//
//        } else {
//            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.EMAIL_IN_USE).build();
//            return new Message.Builder(meta).build();
//        }

    }
}
