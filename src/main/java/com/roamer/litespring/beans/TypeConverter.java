package com.roamer.litespring.beans;

/**
 * 类型转换器接口
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/4 19:33
 */
public interface TypeConverter {

    /**
     * 字符串转换为指定类型
     *
     * @param value
     * @param requiredType
     * @param <T>
     * @return
     * @throws TypeMismatchException
     */
    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;
}
