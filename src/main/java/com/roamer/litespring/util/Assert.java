package com.roamer.litespring.util;

/**
 * 断言
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/21 20:26
 */
public abstract class Assert {

    /**
     * 断言不为null
     *
     * @param o
     * @param message
     */
    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
