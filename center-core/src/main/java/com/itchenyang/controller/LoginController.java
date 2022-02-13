package com.itchenyang.controller;

import com.itchenyang.entity.UserInformation;
import com.itchenyang.result.R;
import com.itchenyang.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "登录接口管理")
@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class LoginController {

    @Resource
    private LoginService loginService;

    @ApiOperation("用户登录接口")
    @PostMapping("/user/login")
    public R login(
            @ApiParam(value = "登录用户名和密码",required = true)
            @RequestBody UserInformation loginVO) {
        return loginService.login(loginVO);
    }
}
