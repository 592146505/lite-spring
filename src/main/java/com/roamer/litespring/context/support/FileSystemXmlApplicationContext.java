package com.roamer.litespring.context.support;

import com.roamer.litespring.beans.factory.support.DefaultBeanFactory;
import com.roamer.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.roamer.litespring.context.ApplicationContext;
import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.core.io.support.FileSystemResource;
import com.roamer.litespring.util.Assert;

import java.io.File;

/**
 * 文件系统Bean容器
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/25 14:27
 */
public class FileSystemXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    public FileSystemXmlApplicationContext(String configFile) {
        this.factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new FileSystemResource(configFile);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return this.factory.getBean(beanId);
    }
}
