package com.roamer.litespring.test.v2;

import com.roamer.litespring.beans.SimpleTypeConverter;
import com.roamer.litespring.beans.TypeConverter;
import com.roamer.litespring.beans.TypeMismatchException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 类型转换测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/4 19:18
 */
public class TypeConverterTest {

    private TypeConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new SimpleTypeConverter();
    }

    @After
    public void tearDown() throws Exception {
        converter = null;
    }

    @Test(expected = TypeMismatchException.class)
    public void testConvertStringToInt() {
        {
            Integer number = converter.convertIfNecessary("1", Integer.class);

            assertEquals(1, number.intValue());
        }
        {
            Integer number = converter.convertIfNecessary("", Integer.class);

            assertNull(number);
        }
        converter.convertIfNecessary("1.0", Integer.class);
    }

    @Test(expected = TypeMismatchException.class)
    public void testConvertStringToBoolean() {
        {
            Boolean value = converter.convertIfNecessary("true", Boolean.class);
            assertTrue(value);
        }
        {
            Boolean value = converter.convertIfNecessary("false", Boolean.class);
            assertFalse(value);
        }
        {
            Boolean value = converter.convertIfNecessary("1", Boolean.class);
            assertTrue(value);
        }
        {
            Boolean value = converter.convertIfNecessary("0", Boolean.class);
            assertFalse(value);
        }

        {
            Boolean value = converter.convertIfNecessary("", Boolean.class);
            assertNull(value);
        }
        converter.convertIfNecessary("as", Integer.class);
    }

}
