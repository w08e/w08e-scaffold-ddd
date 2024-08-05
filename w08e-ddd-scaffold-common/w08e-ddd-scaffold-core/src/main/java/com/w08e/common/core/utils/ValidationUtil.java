package com.w08e.common.core.utils;


import com.w08e.common.core.exception.ServiceException;

/**
 * @author jinyueWang
 * @date 2023/3/29
 * @Desc
 */
public class ValidationUtil {

    public static void isTrue(boolean expect,String msg) {

        if (!expect) {
            throw new ServiceException(msg);
        }
    }

    public static void isFalse(boolean expect, String msg) {
        isTrue(!expect, msg);
    }
}
