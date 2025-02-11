package com.mall_management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall_management.dao.mapper.RoleMapper;
import com.mall_management.dao.mapper.RoleMenuMapper;
import com.mall_management.dao.model.Role;
import com.mall_management.dao.model.RoleMenu;
import com.mall_management.dto.role.UpdateRoleReq;
import com.mall_management.dto.role.AddRoleReq;
import com.mall_management.dto.role.getRoleReq;
import com.mall_management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleMapper roleMapper;



    @Override
    public void addRole(AddRoleReq req) {
        //  添加角色
        Role role = new Role(req.getName(), req.getIntro());
        roleMapper.insert(role); // 插入后，role 的 ID 会自动回填

        //  插入角色和菜单的关联
        if (req.getMenuList() != null && !req.getMenuList().isEmpty()) {
            roleMenuMapper.insertRoleMenu(role.getId(), req.getMenuList());
        }

    }


    @Override
    public  List<Role> getRoleList(getRoleReq req) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();

        // 动态添加查询条件
        if (req.getName() != null && !req.getName().isEmpty()) {
            queryWrapper.eq("name", req.getName());
        }

        if (req.getIntro() != null && !req.getIntro().isEmpty()) {
            queryWrapper.eq("intro", req.getIntro());
        }

        return   roleMapper.selectList(queryWrapper);
    }

    @Override
    public void getRoleData(int id) {
    }


    @Override
    public void updateRole(int id ,  UpdateRoleReq req) {

        // 更新表
        Role existingRole = roleMapper.selectById(id);
        if (req.getName() != null) existingRole.setName(req.getName());
        if (req.getIntro() != null) existingRole.setIntro(req.getIntro());
        roleMapper.updateById(existingRole);

        // 更新联表
        if(req.getMenuList() == null || req.getMenuList().getMenuList() == null) return;

        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);

        // 删除旧的关联
        roleMenuMapper.delete(queryWrapper);

        // TODO 检查菜单合法性

        // 2. 插入新的菜单关联
        roleMenuMapper.insertRoleMenu(id, req.getMenuList().getMenuList());

    }

    @Override
    public void deleteRole(int id) {
//        // 删除旧的关联
//        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("role_id", id);
//        roleMenuMapper.delete(queryWrapper);

        roleMapper.deleteById(id);
    }


}
