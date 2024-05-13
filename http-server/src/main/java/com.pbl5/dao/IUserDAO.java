package com.pbl5.dao;

import com.pbl5.dtos.Pagination.UserPaginationDTO;
import com.pbl5.dtos.UserDTO;
import com.pbl5.models.User;

import java.util.List;

public interface IUserDAO {
    User findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
    User findByEmail(String email,int roleId);
    void save(User user);
    List<User> findAllUserPagination(UserPaginationDTO dto);
    Integer countAllUser();
    void update(User user);
    void resetPassword(String password,String userId);
    void userResetPassword(String password,String email);
    void updatePoint (User user);
}
