package com.itchenyang.service;

import com.itchenyang.entity.ModelTrendEntity;
import com.itchenyang.entity.ModelType;

import java.util.List;
import java.util.Map;

public interface TrendManageService {
    String getTrendCount(String modelName, String modelType, String table);

    Boolean insertTypeTrendCount(List<Map<String, String>> maps);

    Boolean insertNameTrendCount(List<Map<String, String>> newTrend);

    List<ModelTrendEntity> getTrendByType();

    List<String> getModelName();

    ModelTrendEntity getTrendByName(String key);

    String getModelTypeByName(String key);

    List<ModelTrendEntity> getTrendByNameCompare(String type);

    List<ModelType> getCasList();

    String getModel(int parseInt);
}
