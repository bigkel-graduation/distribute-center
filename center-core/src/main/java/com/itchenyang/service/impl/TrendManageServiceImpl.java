package com.itchenyang.service.impl;

import com.itchenyang.mapper.TrendManageMapper;
import com.itchenyang.service.TrendManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TrendManageServiceImpl implements TrendManageService {
    @Resource
    private TrendManageMapper trendManageMapper;

    @Override
    public String getTrendCount(String modelName, String modelType, String table) {
        return trendManageMapper.getTrendCount(modelName,modelType,table);
    }

    @Override
    public Boolean insertTrendCount(List<Map<String, String>> maps) {
        return trendManageMapper.insertTrendCount(maps);
    }
}
