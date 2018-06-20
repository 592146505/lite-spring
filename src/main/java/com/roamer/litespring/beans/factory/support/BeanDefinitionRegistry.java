package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.BeanDefinition;

/**
 * BeanDefinition注册
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 19:32
 */
public interface BeanDefinitionRegistry {


    /**
     * 获取BeanDefinition
     *
     * @param beanId
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * 注册BeanDefinition
     *
     * @param id
     * @param bd
     */
    void registerBeanDefinition(String id, BeanDefinition bd);
}
