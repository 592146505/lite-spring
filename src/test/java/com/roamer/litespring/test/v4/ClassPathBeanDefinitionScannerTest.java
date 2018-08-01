package com.roamer.litespring.test.v4;

import com.roamer.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.roamer.litespring.beans.factory.support.DefaultBeanFactory;
import com.roamer.litespring.context.annotation.ClassPathBeanDefinitionScanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * 注解扫描测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/28 10:17
 */
public class ClassPathBeanDefinitionScannerTest {

    private BeanDefinitionRegistry registry;
    private Verify verify;

    @Before
    public void setUp() throws Exception {
        registry = new DefaultBeanFactory();
        verify = new Verify(registry);
    }

    @After
    public void tearDown() throws Exception {
        verify = null;
        registry = null;
    }

    @Test
    public void testParseScannerBean() {
        String basePackages = "com.roamer.litespring.service,com.roamer.litespring.dao";

        // 扫描带有@Component注解的类
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.doScan(basePackages);


        verify.verifyResult("petStore", "petStore");
        verify.verifyResult("accountDao", null);
        verify.verifyResult("itemDao", null);
    }

}
