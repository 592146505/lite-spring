package com.roamer.litespring.core.type.classreading;

import com.roamer.litespring.core.annotation.AnnotationAttributes;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Map;

/**
 * 注解 属性读取 Visitor
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/22 11:16
 */
public class AnnotationAttributesReadingVisitor extends AnnotationVisitor {

    private final String annotationType;

    private final Map<String, AnnotationAttributes> attributesMap;

    AnnotationAttributes attributes = new AnnotationAttributes();

    public AnnotationAttributesReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap) {
        super(Opcodes.ASM6);
        this.annotationType = annotationType;
        this.attributesMap = attributesMap;
    }

    @Override
    public void visitEnd() {
        attributesMap.put(annotationType, attributes);
    }

    @Override
    public void visit(String attributeName, Object attributeValue) {
        attributes.put(attributeName, attributeValue);
    }
}
