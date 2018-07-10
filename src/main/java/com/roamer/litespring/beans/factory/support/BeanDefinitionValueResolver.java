package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.factory.BeanFactory;
import com.roamer.litespring.beans.factory.config.RuntimeBeanReference;
import com.roamer.litespring.beans.factory.config.TypeStringValue;

/**
 * Bean子标签值 解析器
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/1 15:48
 */
public class BeanDefinitionValueResolver {

    private final BeanFactory factory;

    public BeanDefinitionValueResolver(BeanFactory factory) {
        this.factory = factory;
    }

    /**
     * 根据子标签值进行转换
     *
     * @param value 子标签值定义
     * @return 转换后值
     */
    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            // 引用类型(ref) RuntimeBeanReference，并根据其beanName字段从Bean工厂中查找Bean
            RuntimeBeanReference reference = (RuntimeBeanReference) value;
            String beanName = reference.getBeanName();
            return factory.getBean(beanName);
        } else if (value instanceof TypeStringValue) {
            // 字符型(value) TypeStringValue
            return ((TypeStringValue) value).getValue();
        } else {
            // TODO 未实现类型
            throw new RuntimeException("the value " + value + " has not implemented");
        }
    }
}
