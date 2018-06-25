package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.BeanCreationException;
import com.roamer.litespring.beans.factory.config.ConfigurableBeanFactory;
import com.roamer.litespring.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认Bean工厂
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:15
 */
public class DefaultBeanFactory implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);

    private ClassLoader classLoader;

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinition(String id, BeanDefinition bd) {
        beanDefinitionMap.put(id, bd);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition bd = getBeanDefinition(beanId);
        if (bd == null) {
            return null;
        }
        ClassLoader cl = getClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            // 反射获取类
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }
}
