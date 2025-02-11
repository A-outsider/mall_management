package com.mall_management.service;

import com.mall_management.dao.model.Menu;
import com.mall_management.dto.menu.UpdateMenuReq;
import com.mall_management.dto.menu.addMenuReq;

import java.util.ArrayList;
import java.util.List;

public interface MenuService {
    ArrayList<Menu> getMenuList( String userId);

    void updateMenu(int userId,  UpdateMenuReq req);

    List<String> getPermissionListByLoginId(String loginId);

    void addMenu(addMenuReq req);

    void deleteMenu(int id);

}
