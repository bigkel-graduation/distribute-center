package com.itchenyang.entity;

import lombok.Data;

@Data
public class ModelTrendEntity {
    private Integer id;
    private String modelType;
    private String modelName;
    private String countTrend;
    private Integer count = 0;
}
