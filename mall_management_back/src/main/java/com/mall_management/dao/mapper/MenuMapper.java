package com.mall_management.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall_management.dao.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("<script>" +
            "SELECT * FROM menu WHERE id IN " +
            "<foreach collection='menuIds' item='menuId' open='(' separator=',' close=')'>" +
            "#{menuId}" +
            "</foreach>" +
            "</script>")
    List<Menu> getMenusByIds(@Param("menuIds") List<Integer> menuIds);



    // 根据菜单 ID 查询三级菜单的 permission 字段
    @Select("<script>" +
            "SELECT permission " +
            "FROM menu " +
            "WHERE id IN " +
            "<foreach collection='menuIds' item='menuId' open='(' separator=',' close=')'>" +
            "#{menuId}" +
            "</foreach> " +
            "AND type = 3" +
            "</script>")
    List<String> getPermissionsByMenuIds(@Param("menuIds") List<Integer> menuIds);

}
