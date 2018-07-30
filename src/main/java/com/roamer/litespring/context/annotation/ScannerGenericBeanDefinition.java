package com.roamer.litespring.context.annotation;

import com.roamer.litespring.beans.factory.annotation.AnnotatedBeanDefinition;
import com.roamer.litespring.beans.factory.support.GenericBeanDefinition;
import com.roamer.litespring.core.type.AnnotationMetadata;

/**
 * 扫描形式 BeanDefinition
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/29 12:05
 */
public class ScannerGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;

    public ScannerGenericBeanDefinition(AnnotationMetadata metadata) {
        super();
        this.metadata = metadata;
        super.setBeanClassName(metadata.getClassName());
    }

    @Override
    public AnnotationMetadata getMetadata() {
        return metadata;
    }
}
