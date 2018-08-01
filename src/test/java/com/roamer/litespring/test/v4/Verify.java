package com.roamer.litespring.test.v4;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.roamer.litespring.context.annotation.ScannerGenericBeanDefinition;
import com.roamer.litespring.core.annotation.AnnotationAttributes;
import com.roamer.litespring.core.type.AnnotationMetadata;
import com.roamer.litespring.stereotype.Component;
import com.roamer.litespring.util.StringUtils;
import org.junit.Assert;

class Verify {
    private final BeanDefinitionRegistry registry;

    Verify(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    void verifyResult(String beanId, String attributeValue) {
        // 断言为注解类型BeanDefinition
        BeanDefinition bd = registry.getBeanDefinition(beanId);
        Assert.assertTrue(bd instanceof ScannerGenericBeanDefinition);
        ScannerGenericBeanDefinition sbd = (ScannerGenericBeanDefinition) bd;
        AnnotationMetadata metadata = sbd.getMetadata();

        // 断言使用了@Component
        String annotation = Component.class.getName();
        Assert.assertTrue(metadata.hasAnnotation(annotation));

        // 断言@Component value属性
        if (StringUtils.hasText(attributeValue)) {
            AnnotationAttributes attributes = metadata.getAnnotationAttributes(annotation);
            Assert.assertEquals(attributes.get("value"), attributeValue);
        }

    }
}