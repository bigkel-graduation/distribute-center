package com.itchenyang.service.impl;

import com.itchenyang.entity.Province;
import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserRole;
import com.itchenyang.entity.UserSearchQuery;
import com.itchenyang.exception.Assert;
import com.itchenyang.mapper.LoginMapper;
import com.itchenyang.mapper.UserManageMapper;
import com.itchenyang.result.ResponseEnum;
import com.itchenyang.service.UserManageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Resource
    private LoginMapper loginMapper;
    @Resource
    private UserManageMapper userManageMapper;

    @Override
    public Map<String, Object> getUserListPage(Integer offset, Integer limit, String role, UserSearchQuery searchQuery) {
        /// 查找用户信息
        String[] split = role.split("-");
        int pid = Integer.parseInt(split[0]);
        int cid = Integer.parseInt(split[1]);
        List<UserInformation> userLists = userManageMapper.getUserListPage(offset, limit, pid, cid, searchQuery);
        Integer total = userManageMapper.getUserCount(offset, limit, pid, cid, searchQuery);
        addRole(userLists);
        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("list",userLists);
        return map;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return userManageMapper.deleteUser(id);
    }

    @Override
    public Boolean lockUserOrNot(Integer id, Integer flag) {
        return userManageMapper.lockUserOrNot(id, flag);
    }

    @Override
    public Boolean updateOrInsertUser(UserInformation userInformation) {
        return userManageMapper.updateOrInsertUser(userInformation);
    }

    @Override
    public List<Province> getProvince(Integer pid, Integer cid) {
        return userManageMapper.getProvince(pid, cid);
    }

    public void addRole(List<UserInformation> userLists) {
        for (UserInformation userInformation : userLists) {
            // 查找用户角色
            Integer pid = userInformation.getRolePid();
            Integer cid = userInformation.getRoleCid();
            String role;
            if (pid == 0 && cid == 0) {
                role = "总部";
            }else {
                UserRole userRole = loginMapper.getRoleInfomation(pid,cid);
                Assert.notNull(userRole, ResponseEnum.ROLE_NOT_EXIST);
                if (userRole.getCity() == null || StringUtils.isBlank(userRole.getCity())) {
                    role = userRole.getProvince();
                }else {
                    role = userRole.getProvince() + "-" + userRole.getCity();
                }
            }
            userInformation.setUserRole(role);
        }
    }
}
