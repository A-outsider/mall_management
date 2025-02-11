package com.mall_management.dto.role;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;

@Data
public class AddRoleReq {

    @NotBlank(message = "请输入名字")
    private String name;

    @NotBlank(message = "请输入介绍")
    private String intro;

    // 菜单数组
    private ArrayList<Integer> menuList;

}
