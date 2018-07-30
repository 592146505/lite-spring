package com.roamer.litespring.beans.factory.annotation;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.core.type.AnnotationMetadata;

/**
 * 带注解BeanDefinition
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/29 12:15
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {
    /**
     * 获取注解信息
     *
     * @return 注解元数据信息
     */
    AnnotationMetadata getMetadata();
}
