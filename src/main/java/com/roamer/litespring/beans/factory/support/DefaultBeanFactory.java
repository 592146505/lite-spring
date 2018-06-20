package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.BeanCreationException;
import com.roamer.litespring.beans.factory.BeanDefinitionStoreException;
import com.roamer.litespring.beans.factory.BeanFactory;
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
 * 默认Bean工厂
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:15
 */
public class DefaultBeanFactory implements BeanFactory {

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);

    public DefaultBeanFactory(String configFile) {
        this.loadBeanDefinition(configFile);
    }

    /**
     * 加载文件获取BeanDefinition
     *
     * @param configFile
     */
    private void loadBeanDefinition(String configFile) {
        InputStream is = null;
        try {
            //获取类加载器，加载XML文件
            ClassLoader cl = AbstractClassUtils.getDefaultClassLoader();
            is = cl.getResourceAsStream(configFile);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            //获取遍历子节点
            Element rootElement = doc.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element el = iterator.next();
                //获取id和class
                String id = el.attributeValue(ID_ATTRIBUTE);
                String beanClassName = el.attributeValue(CLASS_ATTRIBUTE);
                //封装为beanDefinition
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                this.beanDefinitionMap.put(id, bd);
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

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition bd = this.getBeanDefinition(beanId);
        if (null == bd) {
            return null;
        }
        ClassLoader cl = AbstractClassUtils.getDefaultClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            //反射获取类
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }
    }
}
