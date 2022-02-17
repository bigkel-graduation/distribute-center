package com.itchenyang.service;

import com.itchenyang.entity.UserInformation;

import java.util.List;

public interface UserManageService {
    List<UserInformation> getUserList(Integer offset, Integer limit, String role);
}
