package com.roamer.litespring.test.v2;


import com.roamer.litespring.beans.propertyeditors.CustomNumberEditor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 数字类型转换测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/3 21:30
 */
public class CustomNumberEditorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConvertString() {
        // 创建数字类型转换器,将String值转换为Integer类型，可以为空字符(返回null)
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);

        editor.setAsText("2");

        Object value = editor.getValue();

        assertTrue(value instanceof Integer);
        assertEquals(2, ((Integer) value).intValue());

        editor.setAsText("");
        assertNull(editor.getValue());

        editor.setAsText("1.0");
    }
}
