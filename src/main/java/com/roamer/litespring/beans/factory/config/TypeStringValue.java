package com.roamer.litespring.beans.factory.config;

/**
 * Bean Property 字符类型
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/1 11:22
 */
public class TypeStringValue {
    private final String value;

    public TypeStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
