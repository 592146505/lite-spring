package com.roamer.litespring.context.support;

import com.roamer.litespring.beans.factory.support.DefaultBeanFactory;
import com.roamer.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.roamer.litespring.context.ApplicationContext;
import com.roamer.litespring.core.io.Resource;

/**
 * ApplicationContext抽象
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/25 15:10
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected DefaultBeanFactory factory;

    public AbstractApplicationContext(String configFile) {
        this.factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(configFile);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return this.factory.getBean(beanId);
    }

    /**
     * 获取文件资源
     *
     * @param path
     * @return
     */
    public abstract Resource getResourceByPath(String path);

}
