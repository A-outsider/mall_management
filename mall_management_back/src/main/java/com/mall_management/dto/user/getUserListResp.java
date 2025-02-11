package com.mall_management.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class getUserListResp {
    private int id;
    private String name;
    private String realName;

    private String phone;
    private int enabled;
    private int roleId;
    private int departmentId;
    private Date createTime;
    private Date updateTime;

}