package com.roamer.litespring.beans.factory.xml;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.BeanDefinitionStoreException;
import com.roamer.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.roamer.litespring.beans.factory.support.GenericBeanDefinition;
import com.roamer.litespring.util.AbstractClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 解析XML获取BeanDefinition
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:15
 */
public class XmlBeanDefinitionReader {

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 加载XML文件注册BeanDefinition
     *
     * @param configFile
     */
    public void loadBeanDefinition(String configFile) {
        InputStream is = null;
        try {
            // 获取类加载器，加载XML文件
            ClassLoader cl = AbstractClassUtils.getDefaultClassLoader();
            is = cl.getResourceAsStream(configFile);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            // 获取遍历子节点
            Element rootElement = doc.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element el = iterator.next();
                // 获取id和class
                String id = el.attributeValue(ID_ATTRIBUTE);
                String beanClassName = el.attributeValue(CLASS_ATTRIBUTE);
                // 封装为beanDefinition
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                this.registry.registerBeanDefinition(id, bd);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + configFile, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}