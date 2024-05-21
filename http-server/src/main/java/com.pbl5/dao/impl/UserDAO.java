package com.pbl5.dao.impl;

import com.pbl5.dao.IUserDAO;

import com.pbl5.dtos.Pagination.UserPaginationDTO;
import com.pbl5.dtos.UserDTO;
import com.pbl5.helpers.BcryptPassword;
import com.pbl5.helpers.IDGeneration;
import com.pbl5.helpers.mapper.CountMapper;
import com.pbl5.helpers.mapper.UserMapper;
import com.pbl5.models.User;


import java.util.List;

import java.util.logging.Logger;

import static com.pbl5.utils.constants.Pagination.PER_PAGE;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {
    private static final Logger logger = Logger.getLogger("UserDAO");

    @Override
    public User findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
        //login
        return null;
    }





    @Override
    public User findByEmail(String email,int roleId) {
        logger.info("Find By Email");
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM users AS U INNER JOIN roles AS R ON U.role_id = R.role_id " +
                " WHERE U.email = ? AND U.role_id= ? ";

        List<User> users = query(sql, new UserMapper(false, true),email, roleId);
        System.out.println("user length"+users.size());
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void save(User user) {
        //register
        // TODO Auto-generated method stub
        logger.info("Save User");



        String sql = "INSERT INTO users (user_id," + "role_id,"  + "email," + "point," + "status,"+"last_name,"+"first_name,"+"phonenumber,"+"gender,"+"address," + "password," + "createdAt," +"date_of_birth, "  + "createdBy)" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        insert(sql, user.getId(), 2, user.getEmail(), user.getPoint(), 1,user.getLastName(),user.getFirstName(),user.getPhoneNumber(),user.getGender(),user.getAddress(), user.getPassword(), user.getCreatedAt(),user.getDateOfBirth(), user.getCreatedBy());
    }

    @Override
    public List<User> findAllUserPagination(UserPaginationDTO dto) {

        String sql="SELECT u.*,r.* FROM pbl5.users AS u INNER JOIN roles AS r on u.role_id = r.role_id WHERE u.role_id= 2 ORDER BY u.createdAt DESC LIMIT "+ PER_PAGE+" OFFSET "+ (dto.getPage() - 1) * PER_PAGE;
       return  query(sql,new UserMapper());
    }

    @Override
    public Integer countAllUser() {
        String sql = "SELECT COUNT(*) as total FROM users ";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }

    @Override
    public void update(User user) {
        String sql="UPDATE users SET point=?,first_name=?,last_name=?,phoneNumber=?" +
                ",status=?,gender=?,address=? WHERE user_id=?";
        update(sql,user.getPoint(),user.getFirstName(),user.getLastName()
                ,user.getPhoneNumber(),user.getStatus(),user.getGender(),user.getAddress(),user.getId());
    }
    //reser password
    @Override
    public void resetPassword(String password,String userId) {
        String sql="UPDATE users SET password = ? WHERE user_id = ?";
        update(sql,password,userId);
    }

    @Override
    public void userResetPassword(String password, String email) {
        String sql="UPDATE users SET password = ? WHERE email = ?";
        update(sql,password,email);
    }

    @Override
    public void updatePoint(User user) {
        String sql="UPDATE users SET point = ? WHERE user_id = ?";
        update(sql,user.getPoint(),user.getId());
    }
}
