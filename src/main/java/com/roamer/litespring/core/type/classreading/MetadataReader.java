package com.roamer.litespring.core.type.classreading;

import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.core.type.AnnotationMetadata;
import com.roamer.litespring.core.type.ClassMetadata;

/**
 * 元数据读取接口
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/22 18:13
 */
public interface MetadataReader {

    /**
     * 获取 class文件 资源描述
     *
     * @return class文件 资源描述
     */
    Resource getResource();

    /**
     * 获取 class 元数据
     *
     * @return class 元数据
     */
    ClassMetadata getClassMetadata();

    /**
     * 获取 注解 元数据
     *
     * @return 注解元数据
     */
    AnnotationMetadata getAnnotationMetadata();
}
