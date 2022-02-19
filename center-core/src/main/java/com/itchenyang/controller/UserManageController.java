package com.itchenyang.controller;

import com.itchenyang.entity.UserSearchQuery;
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
import java.util.Map;

@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class UserManageController {

    @Resource
    public UserManageService userManageService;

    @ApiOperation("用户信息列表查询接口")
    @GetMapping("/user/list/{page}/{limit}")
    public R getUserList(@ApiParam(value = "第几页",required = true) @PathVariable("page") Integer page,
                         @ApiParam(value = "每页数据量",required = true) @PathVariable("limit") Integer limit,
                         @ApiParam(value = "查询对象") UserSearchQuery searchQuery,
                         HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);
        String role = JwtUtils.getRole(token);
        int offset = (page -1) * limit;
        Map<String, Object> map = userManageService.getUserListPage(offset, limit, role, searchQuery);
        return R.ok().playData("userList",map);
    }

    @ApiOperation("逻辑删除用户")
    @DeleteMapping("/user/delete/{id}")
    public R deleteUser(@ApiParam(value = "用户id",required = true) @PathVariable("id") Integer id,
                        HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);
        Boolean result = userManageService.deleteUser(id);
        Assert.isTrue(result, ResponseEnum.DELETE_ERROR);
        return R.ok().playMessage("删除成功");
    }

    @ApiOperation("锁定或解锁用户")
    @PutMapping("/user/lock_or_unlock/{id}/{flag}")
    public R lockUserOrNot(@ApiParam(value = "用户id",required = true) @PathVariable("id") Integer id,
                           @ApiParam(value = "操作",required = true) @PathVariable("flag") Integer flag,
                           HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);
        Boolean result = userManageService.lockUserOrNot(id, flag);
        Assert.isTrue(result, ResponseEnum.ERROR);
        return R.ok();
    }
}
