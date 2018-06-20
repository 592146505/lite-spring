package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.BeanCreationException;
import com.roamer.litespring.beans.factory.BeanFactory;
import com.roamer.litespring.util.AbstractClassUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认Bean工厂
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:15
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinition(String id, BeanDefinition bd) {
        this.beanDefinitionMap.put(id, bd);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition bd = this.getBeanDefinition(beanId);
        if (null == bd) {
            return null;
        }
        ClassLoader cl = AbstractClassUtils.getDefaultClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            // 反射获取类
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }
    }
}
