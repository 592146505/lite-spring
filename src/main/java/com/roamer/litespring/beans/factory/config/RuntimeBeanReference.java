package com.roamer.litespring.beans.factory.config;

/**
 * Bean Property 引用类型
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/30 16:27
 */
public class RuntimeBeanReference {

    /**
     * BeanProperty中ref属性，用于索引Bean进行注入
     */
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
