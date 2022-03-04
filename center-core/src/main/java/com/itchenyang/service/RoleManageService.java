package com.itchenyang.service;

import com.itchenyang.entity.Province;
import com.itchenyang.entity.UserInformation;

import java.util.List;

public interface RoleManageService {
    List<UserInformation> getUserByRole(Integer pid, Integer cid);

    Boolean delUser(Integer id);

    List<Province> canAddRole(Integer pid, Integer cid);

    Boolean addRole(Integer pid, Integer cid);

    Boolean delRole(Integer pid, Integer cid);
}
