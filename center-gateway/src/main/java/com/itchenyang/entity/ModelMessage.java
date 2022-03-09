package com.itchenyang.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ModelMessage {

    private String modelName;
    private String modelType;
    private Date requestTime;
    private String showTime;    // 存放requestTime的展示格式时间 2022-02-04 20:00:00
}
