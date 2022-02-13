package com.itchenyang.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(0,"成功"),
    ERROR(-1,"失败"),

    USER_NULL_ERROR(-601,"获取用户为空"),
    //-1xx 服务器错误
    BAD_SQL_GRAMMAR_ERROR(-101, "sql语法错误"),
    SERVLET_ERROR(-102, "servlet请求异常"), //-2xx 参数校验
    UPLOAD_ERROR(-103, "上传错误"),
    DOWNLOAD_ERROR(-1031,"下载错误"),
    EXPORT_DATA_ERROR(104, "数据导出失败"),

    //-2xx 参数校验
    BORROW_AMOUNT_NULL_ERROR(-201, "借款额度不能为空"),
    MOBILE_NULL_ERROR(-202, "手机号码不能为空"),
    MOBILE_ERROR(-203, "手机号码不正确"),
    PASSWORD_NULL_ERROR(-204, "密码不能为空"),
    CODE_NULL_ERROR(-205, "验证码不能为空"),
    CODE_ERROR(-206, "验证码不正确"),
    MOBILE_EXIST_ERROR(-207, "手机号已被注册"),
    LOGIN_AUTH_ERROR(-211, "未登录"),
    LOGIN_MOBILE_ERROR(-208, "用户不存在"),
    LOGIN_PASSWORD_ERROR(-209, "密码不正确"),
    LOGIN_DISABLED_ERROR(-210, "用户已被禁用"),


    USER_BIND_IDCARD_EXIST_ERROR(-301, "身份证号码已绑定"),
    USER_NO_BIND_ERROR(-302, "支付账户不可用"),
    USER_NO_AMOUNT_ERROR(-303, "借款额度不可用"),
    USER_AMOUNT_LESS_ERROR(-304, "借款额度不足"),
    LEND_INVEST_ERROR(305, "当前状态无法投标"),
    LEND_FULL_SCALE_ERROR(306, "已满标，无法投标"),
    NOT_SUFFICIENT_FUNDS_ERROR(307, "余额不足，请充值"),


    ALIYUN_SMS_LIMIT_CONTROL_ERROR(-502, "短信发送过于频繁"),//业务限流
    ALIYUN_SMS_ERROR(-503, "短信发送失败"),//其他失败


    WEIXIN_CALLBACK_PARAM_ERROR(-601, "回调参数不正确"),
    WEIXIN_FETCH_ACCESSTOKEN_ERROR(-602, "获取access_token失败"),
    WEIXIN_FETCH_USERINFO_ERROR(-603, "获取微信用户信息失败");

    private Integer code;
    private String message;
}
