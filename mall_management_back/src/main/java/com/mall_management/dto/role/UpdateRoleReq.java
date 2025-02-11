package com.mall_management.dto.role;

import lombok.Data;

import java.util.ArrayList;


@Data
public class UpdateRoleReq {

    private String name;

    private String intro;

    private MenuLIstObj menuList;
}
