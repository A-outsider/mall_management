package com.mall_management.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("role_menu")
public class RoleMenu {

    @TableId(value = "id", type = IdType.AUTO)  // 如果有自增主键
    private int id;

    private int roleId;

    private int menuId;
}
