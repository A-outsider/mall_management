package com.mall_management.dto.menu;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class addMenuReq {

    @NotBlank(message = "请输入菜单名称")
    private String name;

    private int type;

    private String url;

    private int sort;

    private int parentId;
}
