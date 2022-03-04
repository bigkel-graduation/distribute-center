package com.itchenyang.controller;

import com.itchenyang.entity.Province;
import com.itchenyang.entity.UserInformation;
import com.itchenyang.exception.Assert;
import com.itchenyang.result.R;
import com.itchenyang.result.ResponseEnum;
import com.itchenyang.service.RoleManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class RoleManageController {

    @Resource
    private RoleManageService roleManageService;

    @ApiOperation("查找与所选角色相关联的用户")
    @GetMapping("/role/get_user_list/{pid}/{cid}")
    public R getUserByRole(@ApiParam(value = "所选角色的pid", required = true) @PathVariable("pid") Integer pid,
                           @ApiParam(value = "所选角色的cid", required = true) @PathVariable("cid") Integer cid,
                           HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);
        List<UserInformation> userList = roleManageService.getUserByRole(pid, cid);
        Integer total = userList.size();
        return R.ok().playData("userList",userList).playData("total",total);
    }

    @ApiOperation("删除所选的用户卡片")
    @DeleteMapping("/role/del_user/{id}")
    public R delUser(@ApiParam(value = "所选用户的id", required = true) @PathVariable("id") Integer id,
                     HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);
        Boolean flag = roleManageService.delUser(id);
        Assert.isTrue(flag,ResponseEnum.DELETE_ERROR);
        return R.ok().playMessage("删除成功");
    }

    @ApiOperation("可供选择的新增角色")
    @GetMapping("/role/can_add_role_list/{pid}/{cid}")
    public R canAddRole(@ApiParam(value = "当前角色的pid",required = true) @PathVariable("pid") Integer pid,
                        @ApiParam(value = "当前角色的cid",required = true) @PathVariable("cid") Integer cid) {
        List<Province> canAddRole = roleManageService.canAddRole(pid,cid);
        return R.ok().playData("canAddRole",canAddRole);
    }

    @ApiOperation("新增角色")
    @PutMapping("/role/add_role/{pid}/{cid}")
    public R addRole(@ApiParam(value = "新增角色的pid",required = true) @PathVariable("pid") Integer pid,
                     @ApiParam(value = "新增角色的cid",required = true) @PathVariable("cid") Integer cid) {
        Boolean flag = roleManageService.addRole(pid,cid);
        Assert.isTrue(flag,ResponseEnum.INSERT_ERROR);
        return R.ok().playMessage("新增角色成功!");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/role/del_role/{pid}/{cid}")
    public R delRole(@ApiParam(value = "删除角色的pid",required = true) @PathVariable("pid") Integer pid,
                     @ApiParam(value = "删除角色的cid",required = true) @PathVariable("cid") Integer cid) {
        Boolean flag = roleManageService.delRole(pid,cid);
        Assert.isTrue(flag,ResponseEnum.DELETE_ERROR);
        return R.ok().playMessage("删除角色成功!");
    }

}
