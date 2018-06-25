package com.roamer.litespring.test.v1;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.BeanCreationException;
import com.roamer.litespring.beans.factory.BeanDefinitionStoreException;
import com.roamer.litespring.beans.factory.support.DefaultBeanFactory;
import com.roamer.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.roamer.litespring.core.io.support.ClassPathResource;
import com.roamer.litespring.service.v1.PetStoreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * BeanFactory 测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:00
 */
public class BeanFactoryTest {
    private DefaultBeanFactory factory = null;
    private XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() throws Exception {
        // 创建
        factory = new DefaultBeanFactory();
        // 创建XmlBeanDefinitionReader用于解析XML
        reader = new XmlBeanDefinitionReader(factory);
    }

    @After
    public void tearDown() throws Exception {
        factory = null;
        reader = null;
    }

    @Test
    public void getBean() {
        reader.loadBeanDefinition(new ClassPathResource("petstore-v1.xml"));
        // 查找BeanDefinition
        BeanDefinition db = factory.getBeanDefinition("petStore");
        assertEquals("com.roamer.litespring.service.v1.PetStoreService", db.getBeanClassName());

        // 获取Bean
        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");
        assertNotNull(petStoreService);
    }

    @Test(expected = BeanCreationException.class)
    public void invalidBean() {
        reader.loadBeanDefinition(new ClassPathResource("petstore-v1.xml"));
        //获取Bean
        factory.getBean("invalidBean");
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void invalidXML() {
        reader.loadBeanDefinition(new ClassPathResource("xxx-v1.xml"));
    }
}
