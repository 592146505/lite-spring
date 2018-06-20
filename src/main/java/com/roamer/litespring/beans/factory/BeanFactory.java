package com.roamer.litespring.beans.factory;

import com.roamer.litespring.beans.BeanDefinition;

/**
 * Bean工厂接口
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:13
 */
public interface BeanFactory {

    /**
     * 获取Bean定义
     *
     * @param beanId
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * 获取Bean
     *
     * @param beanId
     * @return
     */
    Object getBean(String beanId);
}
