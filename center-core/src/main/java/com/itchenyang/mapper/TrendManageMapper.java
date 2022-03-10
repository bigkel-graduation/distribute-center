package com.itchenyang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrendManageMapper {
    String getTrendCount(@Param("modelName") String modelName,
                         @Param("modelType") String modelType,
                         @Param("table") String table);

    Boolean insertTypeTrendCount(@Param("maps") List<Map<String, String>> maps);

    Boolean insertNameTrendCount(@Param("maps") List<Map<String, String>> maps);
}
