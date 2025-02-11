package com.mall_management.dao.model;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@NoArgsConstructor
@TableName("menu")
public class Menu {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String name;

    private String url;

    private String icon;

    private Integer  type;   // 表示几级菜单

    private Integer  parentId;

    private Integer   sort;

    private String permission;

    @TableField(exist = false)
    private ArrayList<Menu> children;

    public Menu( String name, String url, Integer  type, Integer  parentId, Integer   sort) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.parentId = parentId;
        this.sort = sort;
    }

}
