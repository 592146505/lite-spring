package com.roamer.litespring.core.type;

import java.util.Set;

/**
 * 注解 元数据接口
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/21 15:44
 */
public interface AnnotationMetadata {

    /**
     * 是否包含指定注解
     *
     * @param annotationName 注解名称
     * @return 包含:true 不包含:false
     */
    boolean hasAnnotation(String annotationName);

    /**
     * 获取指定注解的属性
     *
     * @param annotationName 注解名称
     * @return 属性Map
     */
    AnnotationAttributes getAnnotationAttributes(String annotationName);

    /**
     * 获取注解类型
     *
     * @return 该类上注解类型
     */
    Set<String> getAnnotationTypes();
}
