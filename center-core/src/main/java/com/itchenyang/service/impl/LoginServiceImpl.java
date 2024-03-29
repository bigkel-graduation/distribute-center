package com.itchenyang.service.impl;

import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserRole;
import com.itchenyang.exception.Assert;
import com.itchenyang.mapper.LoginMapper;
import com.itchenyang.result.R;
import com.itchenyang.result.ResponseEnum;
import com.itchenyang.service.LoginService;
import com.itchenyang.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public R login(UserInformation loginVO) {
        // 查看用户是否存在
        UserInformation userInformation = loginMapper.getUserInformation(loginVO);
        Assert.notNull(userInformation, ResponseEnum.LOGIN_MOBILE_ERROR);
        // 验证密码是否正确
        Assert.equals(loginVO.getPassword(),userInformation.getPassword(),ResponseEnum.LOGIN_PASSWORD_ERROR);
        // 判断用户是否被锁定
        Boolean isLock = userInformation.getIsLock();
        Assert.isFalse(isLock, ResponseEnum.LOGIN_DISABLED_ERROR);
        // 设置用户登录地址
        String ip = "10.50.2." + (new Random().nextInt(36) + 1);
        log.info("用户登录ip为:{}",ip);
        String address = loginMapper.getAddress(ip);
        log.info("此ip对应的地址为:{}",address);
        userInformation.setFromAddress(userInformation.getToAddress());
        userInformation.setToAddress(address);
        Boolean flag = loginMapper.updateIpAddress(userInformation);
        Assert.isTrue(flag, ResponseEnum.IP_ERROR);
        // 查找用户角色
        Integer pid = userInformation.getRolePid();
        Integer cid = userInformation.getRoleCid();
        String role;
        if (pid == 0 && cid == 0) {
            role = "总部";
        }else {
            UserRole userRole = loginMapper.getRoleInfomation(pid, cid);
            if (userRole.getCity() == null || StringUtils.isBlank(userRole.getCity())) {
                role = userRole.getProvince();
            }else {
                role = userRole.getProvince() + "-" + userRole.getCity();
            }
        }
        userInformation.setUserRole(role);
        // 生成token，返回给前端
        String role_num = pid + "-" + cid;
        String token = JwtUtils.createToken(userInformation.getPhone(), userInformation.getUsername(), role_num);
        userInformation.setToken(token);

        return R.ok().playMessage("登录成功").playData("userInformation",userInformation);
    }
}
