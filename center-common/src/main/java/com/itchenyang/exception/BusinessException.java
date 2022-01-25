package com.itchenyang.exception;

import com.itchenyang.result.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException{
    private Integer code;
    private String message;

    /**
     *
     * @param message
     */
    public BusinessException(String message){
        this.message = message;
    }

    /**
     *
     * @param code
     * @param message
     */
    public BusinessException(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @param code
     * @param message
     * @param cause    原始异常信息，代码本身产生的异常信息，非我们自定义抛出的异常信息
     */
    public BusinessException(Integer code,String message,Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BusinessException(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public BusinessException(ResponseEnum responseEnum,Throwable cause) {
        super(cause);
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }
}
