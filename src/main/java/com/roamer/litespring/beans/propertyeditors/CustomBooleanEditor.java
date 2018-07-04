package com.roamer.litespring.beans.propertyeditors;

import com.roamer.litespring.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * 布尔值转换器
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/4 15:48
 */
public class CustomBooleanEditor extends PropertyEditorSupport {

    private static final String VALUE_TRUE = "true";
    private static final String VALUE_FALSE = "false";

    private static final String VALUE_ON = "on";
    private static final String VALUE_OFF = "off";

    private static final String VALUE_YES = "yes";
    private static final String VALUE_NO = "no";

    private static final String VALUE_1 = "1";
    private static final String VALUE_0 = "0";

    private final boolean allowEmpty;

    public CustomBooleanEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 去除不可见字符
        String booleanText = StringUtils.trimAllWhitespace(text);
        if (allowEmpty && !StringUtils.hasText(booleanText)) {
            // 允许空字符，则设置为null
            setValue(null);
        } else if (VALUE_TRUE.equalsIgnoreCase(booleanText) || VALUE_ON.equalsIgnoreCase(booleanText) ||
                VALUE_YES.equalsIgnoreCase(booleanText) || VALUE_1.equalsIgnoreCase(booleanText)) {
            setValue(Boolean.TRUE);
        } else if (VALUE_FALSE.equalsIgnoreCase(booleanText) || VALUE_OFF.equalsIgnoreCase(booleanText) ||
                VALUE_NO.equalsIgnoreCase(booleanText) || VALUE_0.equalsIgnoreCase(booleanText)) {
            setValue(Boolean.FALSE);
        } else {
            throw new IllegalArgumentException("Invalid boolean value [" + text + "]");
        }
    }

    @Override
    public String getAsText() {
        if (Boolean.TRUE.equals(getValue())) {
            return VALUE_TRUE;
        } else if (Boolean.FALSE.equals(getValue())) {
            return VALUE_FALSE;
        }
        return "";
    }
}
