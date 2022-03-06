package com.itchenyang.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class ModelMessage {

    private Integer id;
    private String indexId = UUID.randomUUID().toString();
    private String modelName;
    private String modelType;
    private String requestTime;
}
