package com.mall_management.dao.model;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@TableName("role")
@NoArgsConstructor
public class Role {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String name;

    private String intro;

    @TableField( value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField( value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false) // 数据库中不存在的字段，存储菜单ID列表
    private ArrayList<Integer> menuIds;


    public Role( String name, String intro) {
        this.name = name;
        this.intro = intro;
    }
}
