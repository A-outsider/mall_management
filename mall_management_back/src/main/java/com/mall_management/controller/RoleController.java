package com.mall_management.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.mall_management.dao.model.Role;
import com.mall_management.dto.role.UpdateRoleReq;
import com.mall_management.dto.role.AddRoleReq;
import com.mall_management.dto.role.getRoleReq;
import com.mall_management.service.RoleService;
import com.mall_management.utils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping("/list")
    public Res<List<Role>> getRoleList(@RequestBody @Validated getRoleReq req) {
        try {
            List<Role> roleList = roleService.getRoleList(req);
                return Res.success(roleList);
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }

    @PostMapping("")
    @SaCheckLogin
    public Res addRole(@RequestBody  @Validated AddRoleReq req ) {

    try {
        roleService.addRole(req);
        return Res.success();
    }
        catch (Exception e) {
            return Res.error(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public Res updateRole(@PathVariable("id") int id, @RequestBody @Validated UpdateRoleReq req) {
        try {
            roleService.updateRole(id, req);
            return Res.success();
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @SaCheckLogin
    public Res deleteRole(@PathVariable("id") int id) {
        try {
            roleService.deleteRole(id);
            return Res.success();
        }
        catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }

}
