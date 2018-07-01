package com.roamer.litespring.beans;

/**
 * Bean Property
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/30 16:15
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    /**
     * 是否转换为具体对象
     */
    private boolean converted = false;

    /**
     * 转换过后的具体对象
     */
    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public synchronized boolean isConverted() {
        return converted;
    }


    public Object getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Object convertedValue) {
        converted = true;
        this.convertedValue = convertedValue;
    }
}
