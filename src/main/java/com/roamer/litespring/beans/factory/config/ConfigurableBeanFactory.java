package com.roamer.litespring.beans.factory.config;

import com.roamer.litespring.beans.factory.BeanFactory;

/**
 * 可配置的Bean工厂
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/25 15:50
 */
public interface ConfigurableBeanFactory extends BeanFactory {

    /**
     * 配置ClassLoader
     *
     * @param classLoader
     */
    void setClassLoader(ClassLoader classLoader);

    /**
     * 获取ClassLoader
     *
     * @return
     */
    ClassLoader getClassLoader();
}
