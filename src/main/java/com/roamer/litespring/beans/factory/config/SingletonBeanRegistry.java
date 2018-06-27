package com.roamer.litespring.beans.factory.config;

/**
 * 单例Bean注册接口
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/27 15:46
 */
public interface SingletonBeanRegistry {

    /**
     * 根据BeanName获取Bean
     *
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    /**
     * 注册单例Bean
     *
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);
}
