package com.itchenyang.service;

import com.itchenyang.entity.Province;
import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserSearchQuery;

import java.util.List;
import java.util.Map;

public interface UserManageService {
    Map<String, Object> getUserListPage(Integer offset, Integer limit, String role, UserSearchQuery searchQuery);

    Boolean deleteUser(Integer id);

    Boolean lockUserOrNot(Integer id, Integer flag);

    Boolean updateOrInsertUser(UserInformation userInformation);

    List<Province> getProvince(Integer pid, Integer cid);
}
