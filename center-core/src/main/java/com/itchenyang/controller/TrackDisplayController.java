package com.itchenyang.controller;

import com.itchenyang.exception.Assert;
import com.itchenyang.result.R;
import com.itchenyang.result.ResponseEnum;
import com.itchenyang.service.TrackDisplayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(tags = "用户分布和轨迹管理")
@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class TrackDisplayController {

    @Resource
    private TrackDisplayService trackDisplayService;

    @ApiOperation("用户分布查询接口")
    @GetMapping("/display")
    public R getUserDisplay(HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);
        List<Map<String, Object>> userDisplay = trackDisplayService.getUserDisplay();
        return R.ok().playData("userDisplay", userDisplay);
    }

    @ApiOperation("用户轨迹查询接口")
    @GetMapping("/track")
    public R getUserTrack(HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);
        List<Map<String, Object>> userTrack = trackDisplayService.getUserTrack();
        return R.ok().playData("userTrack", userTrack);
    }
}
