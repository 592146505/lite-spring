package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.ConstructorArgument;
import com.roamer.litespring.beans.PropertyValue;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用BeanDefinition
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 14:36
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;

    private String beanClassName;

    private SoftReference<Class> beanClass;

    private String scope = SCOPE_DEFAULT;

    private boolean singleton = true;

    private boolean prototype = false;

    /**
     * set注入参数
     */
    private List<PropertyValue> propertyValues = new ArrayList<>();

    /**
     * 构造器注入参数
     */
    private ConstructorArgument constructorArgument = new ConstructorArgument();

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getBeanClassName() {
        return beanClassName;
    }

    @Override
    public Class getBeanClass() {
        return beanClass == null ? null : beanClass.get();
    }

    @Override
    public void setBeanClass(Class beanClass) {
        this.beanClass = new SoftReference<>(beanClass);
    }

    @Override
    public boolean isSingleton() {
        return singleton;
    }

    @Override
    public boolean isPrototype() {
        return prototype;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    @Override
    public ConstructorArgument getConstructorArgument() {
        return constructorArgument;
    }

    @Override
    public boolean hasConstructorArgumentValues() {
        return !constructorArgument.isEmpty();
    }

}
