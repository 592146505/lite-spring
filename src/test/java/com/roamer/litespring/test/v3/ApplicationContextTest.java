package com.roamer.litespring.test.v3;

import com.roamer.litespring.context.ApplicationContext;
import com.roamer.litespring.context.support.ClassPathXmlApplicationContext;
import com.roamer.litespring.dao.v3.AccountDao;
import com.roamer.litespring.dao.v3.ItemDao;
import com.roamer.litespring.service.v3.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ApplicationContext接口测试
 *
 * @author roamer
 * @version V3.0
 * @date 2018/7/8 9:47
 */
public class ApplicationContextTest {
    @Test
    public void testBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStore");

        assertNotNull(petStoreService);
        assertNotNull(petStoreService.getAccountDao());
        assertTrue(petStoreService.getAccountDao() instanceof AccountDao);
        assertNotNull(petStoreService.getItemDao());
        assertTrue(petStoreService.getItemDao() instanceof ItemDao);

        assertTrue(1 == petStoreService.getVersion());

    }

}