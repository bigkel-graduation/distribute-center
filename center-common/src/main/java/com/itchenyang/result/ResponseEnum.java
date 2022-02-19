package com.itchenyang.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(200,"操作成功"),
    ERROR(-200,"操作失败"),
    //-1xx 服务器错误
    BAD_SQL_GRAMMAR_ERROR(-101, "sql语法错误"),
    SERVLET_ERROR(-102, "servlet请求异常"), //-2xx 参数校验
    UPLOAD_ERROR(-103, "上传错误"),
    DOWNLOAD_ERROR(-1031,"下载错误"),
    EXPORT_DATA_ERROR(104, "数据导出失败"),

    //-2xx 参数校验
    DELETE_ERROR(-201,"删除失败"),
    MOBILE_NULL_ERROR(-202, "手机号码不能为空"),
    MOBILE_ERROR(-203, "手机号码不正确"),
    PASSWORD_NULL_ERROR(-204, "密码不能为空"),
    CODE_NULL_ERROR(-205, "验证码不能为空"),
    CODE_ERROR(-206, "验证码不正确"),
    MOBILE_EXIST_ERROR(-207, "手机号已被注册"),
    LOGIN_MOBILE_ERROR(-208, "用户不存在"),
    LOGIN_PASSWORD_ERROR(-209, "密码不正确"),
    LOGIN_DISABLED_ERROR(-210, "用户已被锁定"),
    LOGIN_AUTH_ERROR(-211, "未登录");


    private Integer code;
    private String message;
}
