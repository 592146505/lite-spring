package com.roamer.litespring.test.v2;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.PropertyValue;
import com.roamer.litespring.beans.factory.config.RuntimeBeanReference;
import com.roamer.litespring.beans.factory.support.DefaultBeanFactory;
import com.roamer.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.roamer.litespring.core.io.support.ClassPathResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Bean定义解析测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/30 15:58
 */
public class BeanDefinitionTest {
    private DefaultBeanFactory factory = null;
    private XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() throws Exception {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @After
    public void tearDown() throws Exception {
        factory = null;
        reader = null;
    }

    @Test
    public void testGetBeanDefinition() {
        reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        // 获取PropertyValues
        List<PropertyValue> pvs = bd.getPropertyValues();

        assertEquals(2, pvs.size());
        // 断言PropertyValue
        {
            PropertyValue pv = getProperty(pvs, "accountDao");
            assertNotNull(pv);
            assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
        {
            PropertyValue pv = getProperty(pvs, "itemDao");
            assertNotNull(pv);
            assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getProperty(List<PropertyValue> pvs, String name) {
        for (PropertyValue pv : pvs) {
            if (name.equals(pv.getName())) {
                return pv;
            }
        }
        return null;
    }

}
