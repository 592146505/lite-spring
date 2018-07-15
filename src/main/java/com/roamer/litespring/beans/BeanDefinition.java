package com.roamer.litespring.beans;

import java.util.List;

/**
 * BeanDefinition接口
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:18
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    String SCOPE_DEFAULT = "";

    /**
     * 获取BeanId
     *
     * @return
     */
    String getId();

    /**
     * 获取Bean全限定类名
     *
     * @return
     */
    String getBeanClassName();

    /**
     * 获取Bean Class对象
     *
     * @return Bean Class
     */
    Class getBeanClass();

    /**
     * 赋值Bean Class对象
     *
     * @param beanClass
     */
    void setBeanClass(Class beanClass);

    /**
     * 断言为单例模式
     *
     * @return 单例:true 其他:false
     */
    boolean isSingleton();

    /**
     * 断言为原型模式
     *
     * @return 原型:true 其他:false
     */
    boolean isPrototype();

    /**
     * 获取Bean作用域
     *
     * @return scope值
     */
    String getScope();

    /**
     * 设置Bean作用域
     *
     * @param scope scope值
     */
    void setScope(String scope);

    /**
     * 获取Bean Property
     *
     * @return property定义
     */
    List<PropertyValue> getPropertyValues();

    /**
     * 获取构造器注入描述(constructor-arg)
     *
     * @return constructor定义
     */
    ConstructorArgument getConstructorArgument();

    /**
     * 断言有定义 构造函数 参数注入
     *
     * @return 有:true 无:false
     */
    boolean hasConstructorArgumentValues();


}
