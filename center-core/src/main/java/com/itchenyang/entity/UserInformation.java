package com.itchenyang.entity;

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
    private String ip;
    private Integer rolePid;
    private Integer roleCid;
    private String userRole;    // 具体的中文名  浙江-杭州
    private Boolean isDelete;
    private Boolean isLock;
    private String token;
    private String description;
}
