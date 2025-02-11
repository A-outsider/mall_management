package com.mall_management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall_management.dao.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from user where name = #{name}")
//    public User getUserByName(String name);

    @Select("SELECT role_id FROM user WHERE id = #{userId}")
     String getRoleIdByUserId(String userId);

    @Select("<script>" +
            "SELECT id, name, phone , real_name, status, role_id, department_id, create_time, update_time " +
            "FROM user" +
            "</script>")
    @Results(id = "userResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "real_name", property = "realName"),
            @Result(column = "status", property = "status"),
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    ArrayList<User> selectUserList();

}