package com.mall_management.service;


import com.mall_management.dao.model.User;
import com.mall_management.dto.user.addUserReq;
import com.mall_management.dto.user.getUserListReq;
import com.mall_management.dto.user.updateUserReq;

import java.util.ArrayList;

public interface UserService {
    // 登录
    String login(String name, String password);

    void addUser(addUserReq req);

    ArrayList<User> getUserList( getUserListReq req);

    void updateUser(int userId, updateUserReq req);

    void deleteUser(int userId);
}
