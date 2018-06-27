package com.roamer.litespring.beans;

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
     * 获取Bean全限定类名
     *
     * @return
     */
    String getBeanClassName();

    /**
     * 断言为单例模式
     *
     * @return
     */
    boolean isSingleton();

    /**
     * 断言为原型模式
     *
     * @return
     */
    boolean isPrototype();

    /**
     * 获取Bean作用域
     *
     * @return
     */
    String getScope();

    /**
     * 设置Bean作用域
     *
     * @param scope
     */
    void setScope(String scope);
}
