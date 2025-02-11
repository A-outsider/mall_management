package com.mall_management.dto.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class addDepartReq {

    @NotBlank(message = "请输入部门名称")
    private String name;

    @NotBlank(message = "请输入部门负责人")
    private String leader;

    private Integer  parentId;
}
