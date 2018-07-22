package com.roamer.litespring.core.annotation;

import com.roamer.litespring.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 注解属性
 * 拓展自 {@link LinkedHashMap}
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/21 15:48
 */
public class AnnotationAttributes extends LinkedHashMap<String, Object> {
    public AnnotationAttributes(int initialCapacity) {
        super(initialCapacity);
    }

    public AnnotationAttributes() {
    }

    public AnnotationAttributes(Map<? extends String, ?> m) {
        super(m);
    }

    /**
     * 获取 String 类型值
     *
     * @param attributeName K:属性名
     * @return V:值
     */
    public String getString(String attributeName) {
        return doGet(attributeName, String.class);
    }

    /**
     * 获取 String[] 类型值
     *
     * @param attributeName K:属性名
     * @return V:值
     */
    public String[] getStringArray(String attributeName) {
        return doGet(attributeName, String[].class);
    }

    /**
     * 获取 Boolean 类型值
     *
     * @param attributeName K:属性名
     * @return V:值
     */
    public boolean getBoolean(String attributeName) {
        return doGet(attributeName, Boolean.class);
    }

    /**
     * 获取 Number 类型值
     *
     * @param attributeName K:属性名
     * @return V:值
     */
    public <N extends Number> N getNumber(String attributeName) {
        return (N) doGet(attributeName, Integer.class);
    }

    /**
     * 获取 Enum 类型值
     *
     * @param attributeName K:属性名
     * @return V:值
     */
    public <E extends Enum<?>> E getEnum(String attributeName) {
        return (E) doGet(attributeName, Enum.class);
    }

    /**
     * 获取 Class 类型值
     *
     * @param attributeName K:属性名
     * @return V:值
     */
    public <T> Class<? extends T> getClass(String attributeName) {
        return doGet(attributeName, Class.class);
    }

    /**
     * 获取 Class[] 类型值
     *
     * @param attributeName K:属性名
     * @return V:值
     */
    public Class<?>[] getClassArray(String attributeName) {
        return doGet(attributeName, Class[].class);
    }

    /**
     * 根据属性名获取值
     *
     * @param attributeName K:属性名
     * @param expectedType  预期类型
     * @param <T>           预期类型
     * @return V:值
     */
    private <T> T doGet(String attributeName, Class<T> expectedType) {
        Object value = get(attributeName);
        Assert.notNull(value, String.format("Attribute '%s' not found", attributeName));
        return (T) value;
    }
}
