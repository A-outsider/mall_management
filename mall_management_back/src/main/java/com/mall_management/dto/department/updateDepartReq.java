package com.mall_management.dto.department;

import lombok.Data;

@Data
public class updateDepartReq {
    private String name;

    private String leader;


    private Integer  parentId;
}
