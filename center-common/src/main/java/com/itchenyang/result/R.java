package com.itchenyang.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class R {
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private R(){};    // 私有构造函数

    public static R ok() {
        R r = new R();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    public static R setResult(ResponseEnum responseEnum) {
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    // 设置data
    public R playData(String key,Object value) {
        this.data.put(key, value);
        return this;
    }

    public R playData(Map<String,Object> map) {
        this.setData(map);
        return this;
    }

    // 设置具体的打印消息
    public R playMessage(String message) {
        this.setMessage(message);
        return this;
    }

    // 设置具体的状态码
    public R playCode(Integer code) {
        this.setCode(code);
        return this;
    }
}
