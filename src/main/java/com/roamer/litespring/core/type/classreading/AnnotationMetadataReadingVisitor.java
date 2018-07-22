package com.roamer.litespring.core.type.classreading;

import com.roamer.litespring.core.type.AnnotationAttributes;
import com.roamer.litespring.core.type.AnnotationMetadata;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Type;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 注解 元数据读取 Visitor
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/21 15:43
 */
public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements AnnotationMetadata {

    private final Set<String> annotationSet = new LinkedHashSet<>(4);

    private final Map<String, AnnotationAttributes> attributesMap = new LinkedHashMap<>(4);

    @Override
    public boolean hasAnnotation(String annotationName) {
        return attributesMap.containsKey(annotationName);
    }

    @Override
    public AnnotationAttributes getAnnotationAttributes(String annotationName) {
        return attributesMap.get(annotationName);
    }

    @Override
    public Set<String> getAnnotationTypes() {
        return annotationSet;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        // 获取注解类名
        String annotationType = Type.getType(descriptor).getClassName();
        annotationSet.add(annotationType);

        return new AnnotationAttributesReadingVisitor(annotationType, attributesMap);
    }
}
