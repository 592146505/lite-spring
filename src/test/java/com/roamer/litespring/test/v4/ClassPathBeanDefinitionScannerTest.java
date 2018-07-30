package com.roamer.litespring.test.v4;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.support.DefaultBeanFactory;
import com.roamer.litespring.context.annotation.ClassPathBeanDefinitionScanner;
import com.roamer.litespring.context.annotation.ScannerGenericBeanDefinition;
import com.roamer.litespring.core.annotation.AnnotationAttributes;
import com.roamer.litespring.core.type.AnnotationMetadata;
import com.roamer.litespring.stereotype.Component;
import com.roamer.litespring.util.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 注解扫描测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/28 10:17
 */
public class ClassPathBeanDefinitionScannerTest {

    private DefaultBeanFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new DefaultBeanFactory();
    }

    @After
    public void tearDown() throws Exception {
        factory = null;
    }

    @Test
    public void testParseScannerBean() {
        String basePackages = "com.roamer.litespring.service,com.roamer.litespring.dao";

        // 扫描带有@Component注解的类
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackages);


        verifyResult("petStore", "petStore");
        verifyResult("accountDao", null);
        verifyResult("itemDao", null);
    }

    private void verifyResult(String beanId, String attributeValue) {
        // 断言为注解类型BeanDefinition
        BeanDefinition bd = factory.getBeanDefinition(beanId);
        assertTrue(bd instanceof ScannerGenericBeanDefinition);
        ScannerGenericBeanDefinition sbd = (ScannerGenericBeanDefinition) bd;
        AnnotationMetadata metadata = sbd.getMetadata();

        // 断言使用了@Component
        String annotation = Component.class.getName();
        assertTrue(metadata.hasAnnotation(annotation));

        // 断言@Component value属性
        if (StringUtils.hasText(attributeValue)) {
            AnnotationAttributes attributes = metadata.getAnnotationAttributes(annotation);
            assertEquals(attributes.get("value"), attributeValue);
        }

    }

}
