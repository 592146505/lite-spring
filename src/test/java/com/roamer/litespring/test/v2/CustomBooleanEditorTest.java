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

        editor.setAsText("true");
        {
            Object value = editor.getValue();
            assertTrue(value instanceof Boolean);
            assertTrue((Boolean) value);
        }

        editor.setAsText("false");
        {
            Object value = editor.getValue();
            assertTrue(value instanceof Boolean);
            assertFalse((Boolean) value);
        }

        editor.setAsText("on");
        {
            Object value = editor.getValue();
            assertTrue(value instanceof Boolean);
            assertTrue((Boolean) value);
        }

        editor.setAsText("off");
        {
            Object value = editor.getValue();
            assertTrue(value instanceof Boolean);
            assertFalse((Boolean) value);
        }

        editor.setAsText("yes");
        {
            Object value = editor.getValue();
            assertTrue(value instanceof Boolean);
            assertTrue((Boolean) value);
        }

        editor.setAsText("no");
        {
            Object value = editor.getValue();
            assertTrue(value instanceof Boolean);
            assertFalse((Boolean) value);
        }

        editor.setAsText("1");
        {
            Object value = editor.getValue();
            assertTrue(value instanceof Boolean);
            assertTrue((Boolean) value);
        }

        editor.setAsText("0");
        {
            Object value = editor.getValue();
            assertTrue(value instanceof Boolean);
            assertFalse((Boolean) value);
        }

        // 边界条件
        editor.setAsText("");
        assertNull(editor.getValue());


        // 异常
        editor.setAsText("asc");
    }
}
