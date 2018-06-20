package com.roamer.litespring.beans;

/**
 * 基础异常
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 14:55
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
