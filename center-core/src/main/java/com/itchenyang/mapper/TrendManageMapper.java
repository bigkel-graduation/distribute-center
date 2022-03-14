package com.itchenyang.mapper;

import com.itchenyang.entity.ModelTrendEntity;
import com.itchenyang.entity.ModelType;
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

    List<ModelTrendEntity> getTrendByType();

    List<String> getModelName();

    ModelTrendEntity getTrendByName(@Param("key") String key);

    String getModelTypeByName(@Param("key") String key);

    List<ModelTrendEntity> getTrendByNameCompare(@Param("type") String type);

    List<ModelType> getCasList();

    String getModel(@Param("key") int parseInt);
}
