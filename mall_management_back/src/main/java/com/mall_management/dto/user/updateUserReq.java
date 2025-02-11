package com.mall_management.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class updateUserReq {
    private String name;

    private String realName;

    private String phone;

    private Integer enable;

    private Integer roleId;

    private Integer departmentId;
}
