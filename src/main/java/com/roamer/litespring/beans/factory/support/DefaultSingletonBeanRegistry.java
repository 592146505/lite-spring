package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.factory.config.SingletonBeanRegistry;
import com.roamer.litespring.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认单例注册实现
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/27 15:51
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletons = new ConcurrentHashMap<>(64);

    @Override
    public Object getSingleton(String beanName) {
        return singletons.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "'beanName' must not be null");
        Object o = singletons.get(beanName);
        if (o != null) {
            throw new IllegalStateException("Could not register object [" + singletonObject + "] under bean name '" +
                    beanName + "'; there is already object [" + o + "] bound");
        }
        singletons.put(beanName, singletonObject);
    }
}
