package com.mall_management.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.mall_management.dao.mapper.RoleMapper;
import com.mall_management.dao.model.Menu;
import com.mall_management.dto.menu.UpdateMenuReq;
import com.mall_management.dto.menu.addMenuReq;
import com.mall_management.dto.user.addUserReq;
import com.mall_management.service.MenuService;
import com.mall_management.utils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMapper roleMapper;
    @PostMapping("/list")
    @SaCheckLogin
    public Res<List<Menu>> getMenuList() {

        // 通过token中的用户id
       String userId = StpUtil.getLoginIdAsString();
        System.out.println("userId:" + userId);

        try {
            List<Menu> menuList = menuService.getMenuList(userId);
            return Res.success(menuList);
        } catch (Exception e) {
            e.printStackTrace();
            return Res.error(500 , e.getMessage());
        }
    }


    @PostMapping("")
    @SaCheckLogin
    public Res addMenu(@RequestBody  @Validated addMenuReq req) {
        try {
            menuService.addMenu(req);
            return Res.success();
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @SaCheckLogin
    public Res updateMenu( @PathVariable("id") int id , @RequestBody @Validated UpdateMenuReq req) {
        try {
            menuService.updateMenu(id, req);
            return Res.success();
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @SaCheckLogin
    public Res deleteMenu(@PathVariable("id") int id) {
        try {
            menuService.deleteMenu(id);
            return Res.success();
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }

}
