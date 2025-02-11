package com.mall_management.dao.model;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@TableName("user")
@NoArgsConstructor
public class User {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private int id;
    private String name;
    private String realName = "";

    @TableField(value = "phone" , jdbcType = JdbcType.VARCHAR)
    private String phone;

    private int status;

    private String password;
    private int roleId;
    private int departmentId;

    @TableField(value = "create_time", fill = FieldFill.INSERT, jdbcType = JdbcType.TIMESTAMP)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE, jdbcType = JdbcType.TIMESTAMP)
    private Date updateTime;

    public User(String name, String realName, String phone, int status, String password, Integer roleId, Integer departmentId) {
        this.name = name;
        this.realName = realName;
        this.phone = phone;
        this.status = status;
        this.password = password;
        this.roleId = roleId;
        this.departmentId = departmentId;
    }

    public User(int userId, String name, String realName, String phone,Integer status, Integer roleId, Integer departmentId) {
        this.name = name;
        this.realName = realName;
        this.phone = phone;
        this.status = status;
        this.roleId = roleId;
        this.departmentId = departmentId;
    }
}
