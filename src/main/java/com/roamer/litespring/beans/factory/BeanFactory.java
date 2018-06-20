package com.roamer.litespring.beans.factory;

/**
 * Bean工厂接口
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:13
 */
public interface BeanFactory {

    /**
     * 获取Bean
     *
     * @param beanId
     * @return
     */
    Object getBean(String beanId);
}
