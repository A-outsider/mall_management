package com.mall_management.service;

import com.mall_management.dao.model.Role;
import com.mall_management.dto.role.UpdateRoleReq;
import com.mall_management.dto.role.AddRoleReq;
import com.mall_management.dto.role.getRoleReq;

import java.util.List;

public interface RoleService {

    List<Role>  getRoleList(getRoleReq req);

    void getRoleData(int id);

    void addRole(AddRoleReq req);

    void updateRole(int id , UpdateRoleReq req);

    void deleteRole(int id);


}
