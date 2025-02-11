package com.mall_management.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mall_management.dao.model.User;
import com.mall_management.dto.login.loginReq;
import com.mall_management.dto.login.loginResp;
import com.mall_management.dto.user.addUserReq;
import com.mall_management.dto.user.getUserListReq;
import com.mall_management.dto.user.getUserListResp;
import com.mall_management.dto.user.updateUserReq;
import com.mall_management.service.UserService;
import com.mall_management.utils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Res<loginResp> login(@RequestBody  @Validated loginReq loginRequest) {

        try {
            String token = userService.login(loginRequest.getName(), loginRequest.getPassword());

            return Res.success( new loginResp(token), "登录成功");
        } catch (IllegalArgumentException e) {
            // 捕获服务层抛出的参数异常并返回错误信息
            return Res.error(400, e.getMessage());
        } catch (Exception e) {
            // 捕获其他未知异常
            e.printStackTrace();
            return Res.error();
        }

    }

    @PostMapping("/list")
    @SaCheckLogin
//    @SaCheckPermission("user:query")
    public Res<ArrayList<getUserListResp>> getUserList(@RequestBody  @Validated getUserListReq req) {
        try {
            ArrayList<User> users = userService.getUserList(req);
            ArrayList<getUserListResp> resList = new ArrayList<>();

            // 遍历用户数据并转换
            for (User user : users) {
                getUserListResp resp = new getUserListResp(
                        user.getId(),
                        user.getName(),
                        user.getRealName(),
                        user.getPhone(),
                        user.getStatus() ,
                        user.getRoleId(),
                        user.getDepartmentId(),
                        user.getCreateTime(),
                        user.getUpdateTime()
                );
                resList.add(resp);
            }

            return Res.success(resList);
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }

    @PostMapping("")
    @SaCheckLogin
    public Res addUser(@RequestBody  @Validated addUserReq req)  {
        try {
            userService.addUser(req);
            return Res.success();
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Res updateUser(@PathVariable("id") int id , @RequestBody @Validated updateUserReq req) {
        try {
            userService.updateUser(id ,req);
            return Res.success();
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    @SaCheckLogin
    public Res deleteUser(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
            return Res.success();
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }

    }
}

    class  TheUser {
        public void printf(){
            System.out.println(111);
        }
 }