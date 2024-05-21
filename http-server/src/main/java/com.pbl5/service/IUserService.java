package com.pbl5.service;

import com.pbl5.dtos.Pagination.UserPaginationDTO;
import com.pbl5.helpers.respone.Message;
import com.pbl5.models.User;

import java.util.List;

public interface IUserService {
    // manage all user for admin
    Message findAllUserPagination(UserPaginationDTO dto);
    // admin update user
    Message updateUser(User user);
    //admin reset password
    Message resetPassword (User user);
    Message userResetPassword(User user);
    Message findByEmail(User user);
    Message updatePoint(User user);
}
