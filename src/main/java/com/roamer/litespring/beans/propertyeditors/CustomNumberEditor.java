package com.roamer.litespring.beans.propertyeditors;

import com.roamer.litespring.util.NumberUtils;
import com.roamer.litespring.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

/**
 * 数字转换器
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/3 21:40
 */
public class CustomNumberEditor extends PropertyEditorSupport {

    private final Class<? extends Number> numberClass;

    private final NumberFormat numberFormat;

    private final boolean allowEmpty;

    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) {
        this(numberClass, null, allowEmpty);
    }

    public CustomNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty) {
        // 判断是否为Number的子类
        if (numberClass == null || !Number.class.isAssignableFrom(numberClass)) {
            throw new IllegalArgumentException("Property class must be a subclass of Number");
        }
        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        if (value == null) {
            return "";
        }
        if (numberFormat != null) {
            numberFormat.format(value);
        }
        return value.toString();
    }

    @Override
    public void setValue(Object value) {
        // 如果是Number类型的,则转换后传入
        if (value instanceof Number) {
            super.setValue(NumberUtils.convertNumberToTargetClass((Number) value, numberClass));
        } else {
            super.setValue(value);
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (allowEmpty && !StringUtils.hasText(text)) {
            // 允许空值并且不是可见字符则传入null
            setValue(null);
        } else if (numberFormat != null) {
            // 格式化数字
            setValue(NumberUtils.parseNumber(text, numberClass, numberFormat));
        } else {
            // default
            setValue(NumberUtils.parseNumber(text, numberClass));
        }
    }
}
