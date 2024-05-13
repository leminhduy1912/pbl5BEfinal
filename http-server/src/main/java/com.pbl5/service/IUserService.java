package com.pbl5.service;

import com.pbl5.dtos.Pagination.UserPaginationDTO;
import com.pbl5.helpers.respone.Message;
import com.pbl5.models.User;

import java.util.List;

public interface IUserService {



    Message findAllUserPagination(UserPaginationDTO dto);
    Message updateUser(User user);
    Message resetPassword (User user);
    Message userResetPassword(User user);
    Message findByEmail(User user);
    Message updatePoint(User user);
}
