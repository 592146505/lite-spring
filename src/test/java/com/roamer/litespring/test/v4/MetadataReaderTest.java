package com.roamer.litespring.test.v4;

import com.roamer.litespring.core.annotation.AnnotationAttributes;
import com.roamer.litespring.core.io.ClassPathResource;
import com.roamer.litespring.core.type.AnnotationMetadata;
import com.roamer.litespring.core.type.classreading.MetadataReader;
import com.roamer.litespring.core.type.classreading.SimpleMetadataReader;
import com.roamer.litespring.stereotype.Component;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * 元数据 读取测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/22 12:33
 */
public class MetadataReaderTest {

    private ClassPathResource resource;

    @Before
    public void setUp() throws Exception {
        resource = new ClassPathResource("com/roamer/litespring/service/v4/PetStoreService.class");
    }

    @After
    public void tearDown() throws Exception {
        resource = null;
    }

    @Test
    public void testGetMetadata() throws IOException {
        MetadataReader reader = new SimpleMetadataReader(resource);
        AnnotationMetadata amd = reader.getAnnotationMetadata();

        String annotationName = Component.class.getName();

        assertTrue(amd.hasAnnotation(annotationName));

        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotationName);
        assertEquals("petStore", attributes.get("value"));

        assertFalse(amd.isAbstract());
        assertFalse(amd.isFinal());
        assertFalse(amd.isInterface());
        assertTrue(amd.hasSuperClass());

        assertEquals("com.roamer.litespring.service.v4.PetStoreService", amd.getClassName());
    }
}
