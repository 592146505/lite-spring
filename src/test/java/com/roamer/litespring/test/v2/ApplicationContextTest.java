package com.roamer.litespring.test.v2;

import com.roamer.litespring.context.ApplicationContext;
import com.roamer.litespring.context.support.ClassPathXmlApplicationContext;
import com.roamer.litespring.dao.v2.AccountDao;
import com.roamer.litespring.dao.v2.ItemDao;
import com.roamer.litespring.service.v2.PetStoreService;
import com.roamer.litespring.service.v2.PetStoreService1;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ApplicationContext接口测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/30 15:47
 */
public class ApplicationContextTest {

    @Test
    public void testBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService1 petStoreService1 = (PetStoreService1) ctx.getBean("petStore1");

        assertNotNull(petStoreService1);
        assertNotNull(petStoreService1.getAccountDao());
        assertTrue(petStoreService1.getAccountDao() instanceof AccountDao);
        assertNotNull(petStoreService1.getItemDao());
        assertTrue(petStoreService1.getItemDao() instanceof ItemDao);

        assertNotNull(petStoreService1.getOwner());
        assertTrue("roamer".equals(petStoreService1.getOwner()));
    }
}
