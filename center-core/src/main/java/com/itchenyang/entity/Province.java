package com.itchenyang.entity;

import lombok.Data;

import java.util.List;

@Data
public class Province {
    private Integer id;
    private String name;
    private List<City> cityList;
}
