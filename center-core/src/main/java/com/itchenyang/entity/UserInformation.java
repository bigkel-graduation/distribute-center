package com.itchenyang.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation {
    private Integer id;
    private String username;
    private String phone;
    private String password;
    private Integer rolePid;
    private Integer roleCid;
    @ApiModelProperty(value = "角色")
    private String userRole;    // 具体的中文名  浙江-杭州
    private Boolean isDelete;
    private Boolean isLock;
    private String token;
}
