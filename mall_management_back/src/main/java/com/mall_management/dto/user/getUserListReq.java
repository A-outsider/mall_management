package com.mall_management.dto.user;


import lombok.Data;

@Data
public class getUserListReq {
    private String name;
    private String realName;
    private String phone;
    private Integer enabled;
}
