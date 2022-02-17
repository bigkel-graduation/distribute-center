package com.itchenyang.controller;

import com.itchenyang.entity.UserInformation;
import com.itchenyang.exception.Assert;
import com.itchenyang.result.R;
import com.itchenyang.result.ResponseEnum;
import com.itchenyang.service.UserManageService;
import com.itchenyang.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class UserManageController {

    @Resource
    public UserManageService userManageService;

    @ApiOperation("用户信息列表查询接口")
    @GetMapping("/user/list/{offset}/{limit}")
    public R getUserList(@ApiParam(value = "分页偏移量",required = true) @PathVariable("offset") Integer offset,
                         @ApiParam(value = "每页数据量",required = true) @PathVariable("limit") Integer limit,
                         HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);
        String role = JwtUtils.getRole(token);
        List<UserInformation> userList = userManageService.getUserList(offset, limit, role);
        return R.ok().playData("userList",userList);
    }
}
