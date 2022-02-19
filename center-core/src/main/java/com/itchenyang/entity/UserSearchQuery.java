package com.itchenyang.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("搜索对象")
@Data
public class UserSearchQuery {
    @ApiModelProperty(value = "用户名或电话")
    private String key;

    @ApiModelProperty(value = "用户状态")
    private Boolean status;

    @ApiModelProperty(value = "角色")
    private String userRole;
}
