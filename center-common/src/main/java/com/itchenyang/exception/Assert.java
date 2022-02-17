package com.itchenyang.exception;

import com.itchenyang.result.ResponseEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 断言，此处用来代替抛出异常
 */
public class Assert {
    public static void notNull(Object obj, ResponseEnum responseEnum) {
        if (obj == null) {
            throw new BusinessException(responseEnum);
        }
    }

    public static void isNull(Object obj,ResponseEnum responseEnum) {
        if (obj != null) {
            throw new BusinessException(responseEnum);
        }
    }

    public static void isTrue(Boolean bool,ResponseEnum responseEnum) {
        if (!bool) {
            throw new BusinessException(responseEnum);
        }
    }

    public static void isFalse(Boolean bool,ResponseEnum responseEnum) {
        if (bool) {
            throw new BusinessException(responseEnum);
        }
    }

    public static void notEquals(Object obj1,Object obj2,ResponseEnum responseEnum) {
        if (obj1.equals(obj2)) {
            throw new BusinessException(responseEnum);
        }
    }

    public static void equals(Object obj1,Object obj2,ResponseEnum responseEnum) {
        if (!obj1.equals(obj2)) {
            throw new BusinessException(responseEnum);
        }
    }

    public static void notEmpty(String s,ResponseEnum responseEnum) {
        if (StringUtils.isEmpty(s)) {
            throw new BusinessException(responseEnum);
        }
    }

    public static void notBlank(String s,ResponseEnum responseEnum) {
        if (StringUtils.isBlank(s)) {
            throw new BusinessException(responseEnum);
        }
    }

}
