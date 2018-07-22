package com.roamer.litespring.core.type.classreading;

import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.core.type.AnnotationMetadata;
import com.roamer.litespring.core.type.ClassMetadata;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 简单 元数据读取 实现
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/22 18:17
 */
public class SimpleMetadataReader implements MetadataReader {

    private final Resource resource;

    private final ClassMetadata classMetadata;

    private final AnnotationMetadata annotationMetadata;

    public SimpleMetadataReader(Resource resource) throws IOException {
        // 读取class资源文件
        InputStream is = new BufferedInputStream(resource.getInputStream());
        ClassReader classReader;
        try {
            classReader = new ClassReader(is);
        } finally {
            is.close();
        }

        // ASM读取
        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        classReader.accept(visitor, Opcodes.ASM6);

        this.resource = resource;
        this.classMetadata = visitor;
        this.annotationMetadata = visitor;
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    @Override
    public ClassMetadata getClassMetadata() {
        return classMetadata;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return annotationMetadata;
    }
}
