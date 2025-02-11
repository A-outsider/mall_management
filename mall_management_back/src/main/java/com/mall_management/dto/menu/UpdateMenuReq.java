package com.mall_management.dto.menu;

import lombok.Data;

import java.util.ArrayList;


@Data
public class UpdateMenuReq {

    private String name;

    private String url;

    private String icon;

    private Integer parentId;

    private Integer type;

    private String permission;
}
