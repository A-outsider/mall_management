package com.mall_management.service.impl;


import com.mall_management.dao.mapper.MenuMapper;
import com.mall_management.dao.mapper.RoleMenuMapper;
import com.mall_management.dao.mapper.UserMapper;
import com.mall_management.dao.model.Menu;
import com.mall_management.dto.menu.UpdateMenuReq;
import com.mall_management.dto.menu.addMenuReq;
import com.mall_management.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> getPermissionListByLoginId(String loginId) {
        // 1. 根据 loginId 获取角色 ID
        String roleId = userMapper.getRoleIdByUserId(loginId);

        // 2. 根据角色 ID 查询关联的菜单 ID
        List<Integer> menuIds = roleMenuMapper.getMenuIdsByRoleId(roleId);

        // 3. 根据菜单 ID 查询所有三级菜单的 permission 字段
        List<String> permissions = menuMapper.getPermissionsByMenuIds(menuIds);

        // 4. 返回权限列表
        return permissions.stream()
                .filter(permission -> permission != null && !permission.isEmpty()) // 过滤空权限
                .distinct() // 去重
                .collect(Collectors.toList());
    }

    @Override
    public void addMenu(addMenuReq req) {
        Menu menu = new Menu( req.getName(), req.getUrl(), req.getType() , req.getSort(), req.getParentId());
        menuMapper.insert(menu);
    }

    @Override
    public void deleteMenu(int id) {
        menuMapper.deleteById(id);
    }

    public ArrayList<Menu> getMenuList( String userid) {

        // 根据userid 获取角色ID
        String roleId = userMapper.getRoleIdByUserId(userid);

        // 1. 查询角色关联的菜单ID
        ArrayList<Integer> menuIds = roleMenuMapper.getMenuIdsByRoleId(roleId);

        // 1. 查询角色对应的菜单
        ArrayList<Menu> menus = (ArrayList<Menu>) menuMapper.getMenusByIds(menuIds);

        // 2. 查询菜单树
        return getMenuTreeByRole(menus);

    }

    @Override
    public void updateMenu(int id, UpdateMenuReq req) {
         Menu existingMenu = menuMapper.selectById(id);
         if(req.getName() != null) existingMenu.setName(req.getName());
         if(req.getUrl() != null) existingMenu.setUrl(req.getUrl());
         if(req.getParentId() != null) existingMenu.setParentId(req.getParentId());
         if(req.getType() != null) existingMenu.setType(req.getType());

         menuMapper.updateById(existingMenu);
    }



    // 根据角色的菜单 ID 获取菜单树
    public ArrayList<Menu> getMenuTreeByRole(ArrayList<Menu> menus) {

        // 构造顶级菜单 (parentId 为 null)
        ArrayList<Menu> rootMenus = (ArrayList<Menu>) menus.stream()
                .filter(menu -> menu.getParentId() == null)
                .collect(Collectors.toList());

        // 递归构造子菜单
        for (Menu rootMenu : rootMenus) {
            rootMenu.setChildren(getChildren(rootMenu.getId(), menus));
        }

        return rootMenus;
    }

    // 获取子菜单
    private ArrayList<Menu> getChildren(int parentId, ArrayList<Menu> menus) {
        ArrayList<Menu> children = (ArrayList<Menu>) menus.stream()
                .filter(menu -> menu.getParentId() != null && menu.getParentId().equals(parentId))
                .collect(Collectors.toList());

        for (Menu child : children) {
            child.setChildren(getChildren(child.getId(), menus)); // 递归查找子菜单
        }

        return children;
    }
}


