package com.roamer.litespring.test.v2;

import com.roamer.litespring.context.ApplicationContext;
import com.roamer.litespring.context.support.ClassPathXmlApplicationContext;
import com.roamer.litespring.dao.v2.AccountDao;
import com.roamer.litespring.dao.v2.ItemDao;
import com.roamer.litespring.service.v2.PetStoreService;
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
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStore");

        assertNotNull(petStoreService);
        assertNotNull(petStoreService.getAccountDao());
        assertTrue(petStoreService.getAccountDao() instanceof AccountDao);
        assertNotNull(petStoreService.getItemDao());
        assertTrue(petStoreService.getItemDao() instanceof ItemDao);

        assertNotNull(petStoreService.getOwner());
        assertTrue("roamer".equals(petStoreService.getOwner()));

        assertTrue(1 == petStoreService.getVersion());

        assertTrue(1.0 == petStoreService.getMoney());
    }
}
