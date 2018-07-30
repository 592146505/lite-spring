package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.BeanDefinition;

/**
 * beanName 生成器
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/30 15:27
 */
public interface BeanNameGenerator {
    /**
     * 生成 beanName
     *
     * @param definition beanDefinition用于获取定义信息
     * @param registry   注册器
     * @return beanName
     */
    String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);
}
