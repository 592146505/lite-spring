package com.roamer.litespring.test.v2;


import com.roamer.litespring.beans.propertyeditors.CustomBooleanEditor;
import com.roamer.litespring.beans.propertyeditors.CustomNumberEditor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 布尔类型转换测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/3 21:30
 */
public class CustomBooleanEditorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConvertString() {
        // 创建数字类型转换器,将String值转换为Boolean类型，可以为空字符(返回null)
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        assertBooleanResult(editor, "true", true);
        assertBooleanResult(editor, "false", false);
        assertBooleanResult(editor, "on", true);
        assertBooleanResult(editor, "off", false);
        assertBooleanResult(editor, "yes", true);
        assertBooleanResult(editor, "no", false);
        assertBooleanResult(editor, "1", true);
        assertBooleanResult(editor, "0", false);

        // 边界条件
        editor.setAsText("");
        assertNull(editor.getValue());

        // 异常
        editor.setAsText("asc");
    }

    private void assertBooleanResult(CustomBooleanEditor editor, String text, boolean assertResult) {
        editor.setAsText(text);
        Object value = editor.getValue();
        assertTrue(value instanceof Boolean);
        if (assertResult) {
            assertTrue((Boolean) value);
        } else {
            assertFalse((Boolean) value);
        }
    }
}
