package com.roamer.litespring.test.v1;

import com.roamer.litespring.context.ApplicationContext;
import com.roamer.litespring.context.support.ClassPathXmlApplicationContext;
import com.roamer.litespring.service.v1.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ApplicationContext接口测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/21 10:04
 */
public class ApplicationContextTest {

    @Test
    public void getBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStore");
        assertNotNull(petStoreService);
    }
}
