package com.mall_management.service.impl;


import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall_management.dao.mapper.UserMapper;
import com.mall_management.dao.model.User;
import com.mall_management.dto.user.addUserReq;
import com.mall_management.dto.user.getUserListReq;
import com.mall_management.dto.user.updateUserReq;
import com.mall_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;



@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public String login( String name, String password) {
        // 查数据库校验账号密码
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new IllegalArgumentException("账号不存在");
        }

        // 对密码加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("密码错误");
        }

        // 生成token
        StpUtil.login(user.getId());
        return StpUtil.getTokenValue();
    }


    @Override
    public void addUser(addUserReq req) {

        // 对密码加密
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));

        User user = new User(req.getName(), req.getRealName(), req.getPhone(), 1, req.getPassword(), req.getRoleId(), req.getDepartmentId());
        userMapper.insert(user);
    }


    @Override
    public ArrayList<User> getUserList(getUserListReq req) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();

        // 动态添加查询条件
        if (req.getName() != null && !req.getName().isEmpty()) {
            queryWrapper.eq("name", req.getName());
        }
        if (req.getRealName() != null && !req.getRealName().isEmpty()) {
            queryWrapper.eq("real_name", req.getRealName());
        }
        if (req.getPhone() != null && !req.getPhone().isEmpty()) {
            queryWrapper.eq("phone", req.getPhone());
        }
        if (req.getEnabled() != null) {
            queryWrapper.eq("status", req.getEnabled());
        }

        queryWrapper.select(User.class, info -> !info.getColumn().equals("password"));
        List<User> userList = userMapper.selectList(queryWrapper);
        return new ArrayList<>(userList);
    }

    @Override
    public void updateUser(int  userId, updateUserReq req ) {
        User existingUser = userMapper.selectById(userId);
        if (req.getName() != null) {
            existingUser.setName(req.getName());
        }
        if (req.getRealName() != null) {
            existingUser.setRealName(req.getRealName());
        }
        if (req.getPhone() != null) {
            existingUser.setPhone(req.getPhone());
        }
        if (req.getEnable() != null) {
            existingUser.setStatus(req.getEnable());
        }
        if (req.getRoleId() != null) {
            existingUser.setRoleId(req.getRoleId());
        }
        if (req.getDepartmentId() != null) {
            existingUser.setDepartmentId(req.getDepartmentId());
        }

        userMapper.updateById(existingUser);
    }

    @Override
    public void deleteUser(int userId) {
         userMapper.deleteById(userId);
    }
}
