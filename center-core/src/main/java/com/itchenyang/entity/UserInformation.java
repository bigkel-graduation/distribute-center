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
    private String role;
    private UserRole userRole;
    private Boolean isDelete;
    private Boolean isLock;
    private String token;
}
