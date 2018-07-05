package com.roamer.litespring.beans.factory.xml;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.PropertyValue;
import com.roamer.litespring.beans.factory.BeanDefinitionStoreException;
import com.roamer.litespring.beans.factory.config.RuntimeBeanReference;
import com.roamer.litespring.beans.factory.config.TypeStringValue;
import com.roamer.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.roamer.litespring.beans.factory.support.GenericBeanDefinition;
import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * 解析XML获取BeanDefinition
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:15
 */
public class XmlBeanDefinitionReader {

    private static Log logger = LogFactory.getLog(XmlBeanDefinitionReader.class);

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 加载XML文件注册BeanDefinition
     *
     * @param resource
     */
    public void loadBeanDefinition(Resource resource) {
        InputStream is = null;
        try {
            //获取资源输入流
            is = resource.getInputStream();
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
                // 设置scope属性
                if (el.attributeValue(SCOPE_ATTRIBUTE) != null) {
                    bd.setScope(el.attributeValue(SCOPE_ATTRIBUTE));
                }
                parsePropertyElement(el, bd);
                registry.registerBeanDefinition(id, bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(), e);
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

    /**
     * 解析bean property标签
     *
     * @param beanElement
     * @param bd
     */
    public void parsePropertyElement(Element beanElement, BeanDefinition bd) {
        Iterator<Element> iterator = beanElement.elementIterator(PROPERTY_ELEMENT);
        while (iterator.hasNext()) {
            // property标签
            Element propertyElement = iterator.next();
            // 标签name属性，对应实体中的字段名
            String propertyName = propertyElement.attributeValue(NAME_ATTRIBUTE);
            // 字段不能有空格，所以使用hasText函数
            if (!StringUtils.hasText(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }
            // 解析property标签属性
            Object value = parsePropertyValue(propertyElement, bd, propertyName);
            PropertyValue propertyValue = new PropertyValue(propertyName, value);
            // 放置于BeanDefinition中
            bd.getPropertyValues().add(propertyValue);
        }
    }

    /**
     * 解析property标签属性
     *
     * @param propertyElement
     * @param bd
     * @param propertyName
     * @return
     */
    public Object parsePropertyValue(Element propertyElement, BeanDefinition bd, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";

        boolean hasRefAttribute = propertyElement.attributeValue(REF_ATTRIBUTE) != null;
        boolean hasValueAttribute = propertyElement.attributeValue(VALUE_ATTRIBUTE) != null;

        if (hasRefAttribute) {
            // 引用类型
            // property标签ref属性，用于在Bean容器中查找Bean
            String refName = propertyElement.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        } else if (hasValueAttribute) {
            // 字符型
            // property标签value属性，用于赋值Bean属性
            String value = propertyElement.attributeValue(VALUE_ATTRIBUTE);
            return new TypeStringValue(value);
        } else {
            // 未实现的功能抛出异常
            throw new RuntimeException(elementName + "must specify a ref or value");
        }
    }


}