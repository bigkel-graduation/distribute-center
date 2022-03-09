package com.itchenyang.service;

import java.util.List;
import java.util.Map;

public interface TrendManageService {
    String getTrendCount(String modelName, String modelType, String table);

    Boolean insertTrendCount(List<Map<String, String>> maps);
}
