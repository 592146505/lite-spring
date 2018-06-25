package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.BeanDefinition;

/**
 * 通用BeanDefinition
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 14:36
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;

    private String beanClassName;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        return beanClassName;
    }
}
