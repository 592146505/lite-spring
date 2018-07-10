package com.roamer.litespring.test.v3;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.ConstructorArgument;
import com.roamer.litespring.beans.ConstructorArgument.ValueHolder;
import com.roamer.litespring.beans.factory.config.RuntimeBeanReference;
import com.roamer.litespring.beans.factory.config.TypeStringValue;
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
 * @version V3.0
 * @date 2018/6/30 15:58
 */
public class BeanDefinitionTest {
    private DefaultBeanFactory factory = null;
    private XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() throws Exception {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));
    }

    @After
    public void tearDown() throws Exception {
        factory = null;
        reader = null;
    }

    @Test
    public void testConstructorArgument() {
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        assertNotNull(bd);
        assertEquals("com.roamer.litespring.service.v3.PetStoreService", bd.getBeanClassName());

        ConstructorArgument args = bd.getConstructorArgument();
        List<ValueHolder> valueHolders = args.getValueHolders();
        // 判断参数个数
        assertEquals(3, valueHolders.size());

        assertEquals("accountDao", ((RuntimeBeanReference) valueHolders.get(0).getValue()).getBeanName());

        assertEquals("itemDao", ((RuntimeBeanReference) valueHolders.get(1).getValue()).getBeanName());

        assertEquals("1", ((TypeStringValue) valueHolders.get(2).getValue()).getValue());


    }


}
