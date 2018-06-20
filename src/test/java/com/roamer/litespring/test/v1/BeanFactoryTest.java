package com.roamer.litespring.test.v1;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.BeanCreationException;
import com.roamer.litespring.beans.factory.BeanDefinitionStoreException;
import com.roamer.litespring.beans.factory.BeanFactory;
import com.roamer.litespring.beans.factory.support.DefaultBeanFactory;
import com.roamer.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.roamer.litespring.service.v1.PetStoreService;
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

    @Test
    public void getBean() {
        // 创建Bean工厂
        DefaultBeanFactory factory = new DefaultBeanFactory();
        // 解析xml
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition("petstore-v1.xml");
        // 查找BeanDefinition
        BeanDefinition db = factory.getBeanDefinition("petStore");
        assertEquals("com.roamer.litespring.service.v1.PetStoreService", db.getBeanClassName());

        // 获取Bean
        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");
        assertNotNull(petStoreService);
    }

    @Test(expected = BeanCreationException.class)
    public void invalidBean() {
        // 创建Bean工厂
        DefaultBeanFactory factory = new DefaultBeanFactory();
        // 解析xml
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition("petstore-v1.xml");
        //获取Bean
        factory.getBean("invalidBean");
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void invalidXML() {
        // 创建Bean工厂
        DefaultBeanFactory factory = new DefaultBeanFactory();
        // 解析xml
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition("***-v1.xml");
    }
}
