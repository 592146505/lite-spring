package com.roamer.litespring.test.v3;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.support.ConstructorResolver;
import com.roamer.litespring.beans.factory.support.DefaultBeanFactory;
import com.roamer.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.roamer.litespring.core.io.support.ClassPathResource;
import com.roamer.litespring.dao.v3.AccountDao;
import com.roamer.litespring.dao.v3.ItemDao;
import com.roamer.litespring.service.v3.PetStoreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 构造器注入测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/10 20:18
 */
public class ConstructorResolverTest {

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
    public void testConstructorResolver() {
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        // 解析器需要持有BeanFactory实例，以便查找Bean
        ConstructorResolver resolver = new ConstructorResolver(factory);
        PetStoreService petStoreService = (PetStoreService) resolver.autowireConstructor(bd);
        assertNotNull(petStoreService);

        assertEquals(1, petStoreService.getVersion());

        assertNotNull(petStoreService.getAccountDao());
        assertTrue(petStoreService.getAccountDao() instanceof AccountDao);
        assertNotNull(petStoreService.getItemDao());
        assertTrue(petStoreService.getItemDao() instanceof ItemDao);
    }
}
