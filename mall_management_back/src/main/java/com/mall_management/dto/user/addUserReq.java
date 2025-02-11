package com.mall_management.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class addUserReq {
    @NotBlank(message = "请输入名字")
    private String name;

    @NotBlank(message = "请输入密码")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d\\W]{6,18}$", message = "密码必须包含字母和数字，且在6-18位之间")
    private String password;

    private String realName;

    private String phone;

    private Integer RoleId;

    private Integer departmentId;
}
