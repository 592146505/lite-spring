package com.roamer.litespring.test.v4;

import com.roamer.litespring.context.ApplicationContext;
import com.roamer.litespring.context.support.ClassPathXmlApplicationContext;
import com.roamer.litespring.dao.v4.AccountDao;
import com.roamer.litespring.dao.v4.ItemDao;
import com.roamer.litespring.service.v4.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 基于注解
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/15 14:58
 */
public class ApplicationContextTest {

    @Test
    public void testBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v4.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStore");

        assertNotNull(petStoreService);
        assertNotNull(petStoreService.getAccountDao());
        assertTrue(petStoreService.getAccountDao() instanceof AccountDao);
        assertNotNull(petStoreService.getItemDao());
        assertTrue(petStoreService.getItemDao() instanceof ItemDao);
    }
}
