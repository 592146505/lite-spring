package com.roamer.litespring.test.v4;

import com.roamer.litespring.core.io.ClassPathResource;
import com.roamer.litespring.core.type.AnnotationAttributes;
import com.roamer.litespring.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.roamer.litespring.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import static org.junit.Assert.*;

/**
 * ASM 操作 Class元数据
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/19 23:04
 */
public class ClassReaderTest {

    private ClassPathResource resource;

    private ClassReader classReader;

    @Before
    public void setUp() throws Exception {
        resource = new ClassPathResource("com/roamer/litespring/service/v4/PetStoreService.class");
        classReader = new ClassReader(resource.getInputStream());
    }

    @After
    public void tearDown() throws Exception {
        resource = null;
        classReader = null;
    }

    @Test
    public void testGetClassMetadata() {
        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);

        // 断言不为接口
        assertFalse(visitor.isInterface());
        // 断言不为抽象类
        assertFalse(visitor.isAbstract());
        // 断言无final修饰
        assertFalse(visitor.isFinal());

        // 断言类名
        assertEquals("com.roamer.litespring.service.v4.PetStoreService", visitor.getClassName());
        // 断言父类
        assertTrue(visitor.hasSuperClass());
        assertEquals("java.lang.Object", visitor.getSuperClassName());
        // 断言无接口
        assertEquals(0, visitor.getInterfaceNames().length);
    }

    @Test
    public void testGetAnnotationMetadata() {
        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);

        // 断言包含指定注解
        String annotationName = "com.roamer.litespring.stereotype.Component";
        assertTrue(visitor.hasAnnotation(annotationName));

        // 断言注解属性
        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotationName);
        assertEquals("petStore", attributes.get("value"));
    }
}
