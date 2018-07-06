package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.PropertyValue;
import com.roamer.litespring.beans.SimpleTypeConverter;
import com.roamer.litespring.beans.factory.BeanCreationException;
import com.roamer.litespring.beans.factory.config.ConfigurableBeanFactory;
import com.roamer.litespring.util.ClassUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认Bean工厂
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:15
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {

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
        // 如果是单例，则通过单例获取,不存在则通过单例模式创建
        if (bd.isSingleton()) {
            Object singletonBean = getSingleton(beanId);
            if (singletonBean == null) {
                singletonBean = createBean(bd);
                registerSingleton(beanId, singletonBean);
            }
            return singletonBean;
        }
        return createBean(bd);
    }

    private Object createBean(BeanDefinition bd) {
        // 创建Bean实例
        Object bean = instantiateBean(bd);
        // 设置Bean属性
        populateBeanUseCommonsBeanUtils(bd, bean);
        return bean;
    }

    /**
     * 根据Bean定义创建Bean
     *
     * @param bd
     * @return
     */
    private Object instantiateBean(BeanDefinition bd) {
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


    /**
     * 注入bean的字段值
     *
     * @param bd
     * @param bean
     */
    private void populateBean(BeanDefinition bd, Object bean) {
        List<PropertyValue> values = bd.getPropertyValues();
        if (values == null || values.isEmpty()) {
            return;
        }

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        // 类型转换器
        SimpleTypeConverter converter = new SimpleTypeConverter();
        try {
            // 获取BeanInfo
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            // 获取属性描述符
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyValue pv : values) {
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                // 得到转换后的值（如果时引用类型，则转换为具体的bean）
                Object resolvedValue = resolver.resolveValueIfNecessary(originalValue);
                // 循环调用属性的set方法
                for (PropertyDescriptor pd : pds) {
                    if (propertyName.equals(pd.getName())) {
                        // 将值转换为正确的可注入类型
                        Object convertedValue = converter.convertIfNecessary(resolvedValue, pd.getPropertyType());
                        pd.getWriteMethod().invoke(bean, convertedValue);
                    }
                }

            }
        } catch (Exception e) {
            throw new BeanCreationException("Failed to obtain BeanInfo for class [" + bean.getClass() + "]", e);
        }
    }

    /**
     * 使用Apache Commons BeanUtils对Bean进行属性注入
     *
     * @param bd
     * @param bean
     */
    private void populateBeanUseCommonsBeanUtils(BeanDefinition bd, Object bean) {
        List<PropertyValue> values = bd.getPropertyValues();
        if (values == null || values.isEmpty()) {
            return;
        }

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        try {
            for (PropertyValue pv : values) {
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                // 得到转换后的值（如果时引用类型，则转换为具体的bean）
                Object resolvedValue = resolver.resolveValueIfNecessary(originalValue);
                // 使用Apache Commons BeanUtils对Bean进行属性注入
                BeanUtils.setProperty(bean, propertyName, resolvedValue);
            }
        } catch (Exception e) {
            throw new BeanCreationException("Failed to obtain BeanInfo for class [" + bean.getClass() + "]", e);
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
