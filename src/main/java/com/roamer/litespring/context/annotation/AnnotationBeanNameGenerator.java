package com.roamer.litespring.context.annotation;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.annotation.AnnotatedBeanDefinition;
import com.roamer.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.roamer.litespring.beans.factory.support.BeanNameGenerator;
import com.roamer.litespring.core.annotation.AnnotationAttributes;
import com.roamer.litespring.core.type.AnnotationMetadata;
import com.roamer.litespring.util.ClassUtils;
import com.roamer.litespring.util.StringUtils;

import java.beans.Introspector;
import java.util.Set;

/**
 * 带注解的bean name生成器
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/30 15:31
 */
public class AnnotationBeanNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if (definition instanceof AnnotatedBeanDefinition) {
            String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
            if (StringUtils.hasText(beanName)) {
                return beanName;
            }
        }
        return buildDefaultBeanName(definition, registry);
    }

    /**
     * 根据注解value属性获取名称
     *
     * @param definition 带注解的beanDefinition
     * @return 注解 value属性值
     */
    private String determineBeanNameFromAnnotation(AnnotatedBeanDefinition definition) {
        AnnotationMetadata amd = definition.getMetadata();
        Set<String> types = amd.getAnnotationTypes();
        for (String type : types) {
            // TODO 所有的注解value属性都可以？
            AnnotationAttributes attributes = amd.getAnnotationAttributes(type);
            Object value = attributes.get("value");
            if (value instanceof String && StringUtils.hasLength((String) value)) {
                return (String) value;
            }
        }
        return null;
    }

    private String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return buildDefaultBeanName(definition);
    }

    private String buildDefaultBeanName(BeanDefinition definition) {
        // 获取类名称(非全路径名)
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());
        // 首字母小写
        return Introspector.decapitalize(shortClassName);
    }


}
