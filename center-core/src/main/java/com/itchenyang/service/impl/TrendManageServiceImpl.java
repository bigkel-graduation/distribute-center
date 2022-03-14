package com.itchenyang.service.impl;

import com.itchenyang.entity.ModelTrendEntity;
import com.itchenyang.entity.ModelType;
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
    public Boolean insertTypeTrendCount(List<Map<String, String>> maps) {
        return trendManageMapper.insertTypeTrendCount(maps);
    }

    @Override
    public Boolean insertNameTrendCount(List<Map<String, String>> maps) {
        return trendManageMapper.insertNameTrendCount(maps);
    }

    @Override
    public List<ModelTrendEntity> getTrendByType() {
        List<ModelTrendEntity> trends = trendManageMapper.getTrendByType();
        for (ModelTrendEntity entity : trends) {
            int count = 0;
            for (String s : entity.getCountTrend().split(",")) {
                count += Integer.parseInt(s);
            }
            entity.setCount(count);
        }
        return trends;
    }

    @Override
    public List<String> getModelName() {
        return trendManageMapper.getModelName();
    }

    @Override
    public ModelTrendEntity getTrendByName(String key) {
        return trendManageMapper.getTrendByName(key);
    }

    @Override
    public String getModelTypeByName(String key) {
        return trendManageMapper.getModelTypeByName(key);
    }

    @Override
    public List<ModelTrendEntity> getTrendByNameCompare(String type) {
        List<ModelTrendEntity> trends = trendManageMapper.getTrendByNameCompare(type);
        for (ModelTrendEntity entity : trends) {
            int count = 0;
            for (String s : entity.getCountTrend().split(",")) {
                count += Integer.parseInt(s);
            }
            entity.setCount(count);
        }
        return trends;
    }

    @Override
    public List<ModelType> getCasList() {
        return trendManageMapper.getCasList();
    }

    @Override
    public String getModel(int parseInt) {
        return trendManageMapper.getModel(parseInt);
    }
}
