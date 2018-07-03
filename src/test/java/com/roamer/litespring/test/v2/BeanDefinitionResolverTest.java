package com.roamer.litespring.test.v2;

import com.roamer.litespring.beans.factory.config.RuntimeBeanReference;
import com.roamer.litespring.beans.factory.config.TypeStringValue;
import com.roamer.litespring.beans.factory.support.BeanDefinitionValueResolver;
import com.roamer.litespring.beans.factory.support.DefaultBeanFactory;
import com.roamer.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.roamer.litespring.core.io.support.ClassPathResource;
import com.roamer.litespring.dao.v2.AccountDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Bean定义测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/1 15:39
 */
public class BeanDefinitionResolverTest {

    private DefaultBeanFactory factory;
    private XmlBeanDefinitionReader reader;
    BeanDefinitionValueResolver resolver;

    @Before
    public void setUp() throws Exception {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));
        resolver = new BeanDefinitionValueResolver(factory);
    }

    @After
    public void tearDown() throws Exception {
        factory = null;
        reader = null;
    }

    /**
     * property标签ref属性转换为Bean
     */
    @Test
    public void testResolverRuntimeBeanReference() {
        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object o = resolver.resolveValueIfNecessary(reference);

        assertNotNull(o);
        assertTrue(o instanceof AccountDao);

    }

    /**
     * property标签value属性值转换为具体值
     */
    @Test
    public void testResolverTypeStringValue() {
        TypeStringValue value = new TypeStringValue("test");
        Object o = resolver.resolveValueIfNecessary(value);

        assertNotNull(o);
        assertEquals("test", o);

    }
}
