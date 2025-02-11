package com.mall_management.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall_management.dao.model.RoleMenu;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Select("SELECT menu_id FROM role_menu WHERE role_id = #{roleId}")
    ArrayList<Integer> getMenuIdsByRoleId(String roleId);

    @Insert("<script>" +
            "INSERT INTO role_menu (role_id, menu_id) VALUES " +
            "<foreach collection='menuIds' item='menuId' separator=','>" +
            "(#{roleId}, #{menuId})" +
            "</foreach>" +
            "</script>")
    void insertRoleMenu(@Param("roleId") int roleId, @Param("menuIds") List<Integer> menuIds);


}
