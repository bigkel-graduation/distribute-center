package com.itchenyang.controller;

import com.itchenyang.entity.ModelTrendEntity;
import com.itchenyang.entity.ModelType;
import com.itchenyang.exception.Assert;
import com.itchenyang.result.R;
import com.itchenyang.result.ResponseEnum;
import com.itchenyang.service.TrendManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "服务趋势管理")
@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class TrendManageController {

    @Resource
    private TrendManageService trendManageService;

    @ApiOperation("服务类型趋势查询接口")
    @GetMapping("/trend/type")
    public R getTrendByType(HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);
        List<ModelTrendEntity> trend = trendManageService.getTrendByType();
        return R.ok().playData("trend",trend);
    }

    @ApiOperation("服务名称趋势查询接口")
    @GetMapping("/trend/name")
    public R getTrendByName(@RequestParam(value = "key", required = false) String key,
                            HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);

        // 查询当前模块是否存在
        List<String> modelNames = trendManageService.getModelName();
        if (key == null || StringUtils.isBlank(key)) {
            // 若key为空，则给列表中的第一个作为默认值
            key = modelNames.get(0);
        }
        if (modelNames.contains(key)) {
            ModelTrendEntity entity = trendManageService.getTrendByName(key);
            return R.ok().playData("trend",entity);
        }
        return R.error().playMessage("当前服务名称不存在!");
    }

    @ApiOperation("同一类型服务趋势查询接口")
    @GetMapping("/trend/name/compare")
    public R getTrendByNameCompare(@RequestParam(value = "key", required = false) String key,
                            HttpServletRequest request) {
        String token = request.getHeader("X-token");
        Assert.notBlank(token, ResponseEnum.LOGIN_AUTH_ERROR);

        // 查询当前模块是否存在
        List<String> modelNames = trendManageService.getModelName();
        if (key == null || StringUtils.isBlank(key)) {
            // 若key为空，则给列表中的第一个作为默认值
            key = modelNames.get(0);
        }
        if (modelNames.contains(key)) {
            // 根据key查type
            String type = trendManageService.getModelTypeByName(key);
            // 查出这个类型中的所有服务以及其数据
            List<ModelTrendEntity> entity = trendManageService.getTrendByNameCompare(type);
            return R.ok().playData("trend",entity);
        }
        return R.error().playMessage("当前服务名称不存在!");
    }

    @ApiOperation("级联列表查询接口")
    @GetMapping("/casca/list")
    public R getCasList() {
        List<ModelType> modelTypeList = trendManageService.getCasList();
        return R.ok().playData("list",modelTypeList);
    }

    @ApiOperation("根据级联查询趋势接口")
    @GetMapping("/trend/casda/{flag}")
    public R getTrendByCas(@PathVariable("flag") String flag) {
        // 根据flag查询对应的服务名称
        String modelName = trendManageService.getModel(Integer.parseInt(flag));
        return R.ok().playData("modelName",modelName);
    }
}
