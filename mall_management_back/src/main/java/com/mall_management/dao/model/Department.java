package com.mall_management.dao.model;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@TableName("department")
public class Department {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String name;

    private String leader;

    private Integer  parentId;

    @TableField( value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField( value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public Department( String name, String leader, Integer  parentId) {
        this.name = name;
        this.leader = leader;
        this.parentId = parentId;
    }
}
