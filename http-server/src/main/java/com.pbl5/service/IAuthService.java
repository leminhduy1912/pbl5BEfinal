package com.pbl5.service;

import com.pbl5.dtos.UserDTO;
import com.pbl5.dtos.UserSignInDTO;
import com.pbl5.helpers.respone.Message;
import com.pbl5.utils.exceptions.dbException.CreateFailedException;
import com.pbl5.utils.exceptions.dbException.DuplicateEntryException;
import com.pbl5.utils.exceptions.dbException.NotFoundException;
import com.pbl5.utils.exceptions.dbException.UnexpectedException;
import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.apache.hc.core5.http.NotImplementedException;

public interface IAuthService {
    Message Login(UserSignInDTO dto) throws UnexpectedException, NotFoundException, InvalidCredentialsException;




    Message Register(UserDTO user) throws DuplicateEntryException, CreateFailedException, UnexpectedException, NotFoundException, InvalidCredentialsException, NotImplementedException;

    Message InspectorRegister(UserDTO dto, String userId) throws DuplicateEntryException, CreateFailedException, NotImplementedException;


    Message ResetPassword(String id,String userId) throws NotFoundException, UnexpectedException;
    Message checkEmailExistAndSendVerifyMailCode(String email,int roleId);
}
