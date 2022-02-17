package com.itchenyang.service.impl;

import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserRole;
import com.itchenyang.mapper.LoginMapper;
import com.itchenyang.mapper.UserManageMapper;
import com.itchenyang.service.UserManageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Resource
    private LoginMapper loginMapper;
    @Resource
    private UserManageMapper userManageMapper;

    @Override
    public List<UserInformation> getUserList(Integer offset, Integer limit, String role) {
        /// 查找用户信息
        String[] split = role.split("-");
        int pid = Integer.parseInt(split[0]);
        int cid = Integer.parseInt(split[1]);
        List<UserInformation> userLists = userManageMapper.getUserList(offset, limit, pid, cid);
        addRole(userLists);
        return userLists;
    }

    public void addRole(List<UserInformation> userLists) {
        for (UserInformation userInformation : userLists) {
            // 查找用户角色
            UserRole userRole = loginMapper.getRoleInfomation(userInformation.getRolePid(), userInformation.getRoleCid());
            String role;
            if (userRole.getCity() == null || StringUtils.isBlank(userRole.getCity())) {
                role = userRole.getProvince();
            }else {
                role = userRole.getProvince() + "-" + userRole.getCity();
            }
            userInformation.setUserRole(role);
        }
    }
}
