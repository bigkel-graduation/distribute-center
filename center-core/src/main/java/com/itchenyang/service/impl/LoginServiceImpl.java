package com.itchenyang.service.impl;

import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserRole;
import com.itchenyang.exception.Assert;
import com.itchenyang.mapper.LoginMapper;
import com.itchenyang.result.R;
import com.itchenyang.result.ResponseEnum;
import com.itchenyang.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public R login(UserInformation loginVO) {
        UserInformation userInformation = loginMapper.getUserInformation(loginVO);
        Assert.notNull(userInformation, ResponseEnum.USER_NULL_ERROR);
        String[] role = userInformation.getRole().split("-");
        UserRole userRole;
        if (role.length > 1) {
            userRole = loginMapper.getRoleInfomation(new Integer(role[0]), new Integer(role[1]));
            userInformation.setUserRole(userRole);
        }else {
            userRole = loginMapper.getRoleInfomation(new Integer(role[0]),null);
            userInformation.setUserRole(userRole);
        }
        return R.ok().playData("userInformation",userInformation);
    }
}
