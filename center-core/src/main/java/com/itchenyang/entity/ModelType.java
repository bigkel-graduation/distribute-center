package com.itchenyang.entity;

import lombok.Data;

import java.util.List;

@Data
public class ModelType {
    private Integer id;
    private String name;
    private List<ModelName> childList;
}
