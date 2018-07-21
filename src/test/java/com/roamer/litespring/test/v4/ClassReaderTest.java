package com.roamer.litespring.test.v4;

import com.roamer.litespring.core.io.ClassPathResource;
import com.roamer.litespring.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * ASM 操作 Class元数据
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/19 23:04
 */
public class ClassReaderTest {

    @Test
    public void testGetClassMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/roamer/litespring/service/v4/PetStoreService.class");
        ClassReader classReader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();

        classReader.accept(visitor, ClassReader.SKIP_DEBUG);

        assertFalse(visitor.isInterface());
        assertFalse(visitor.isAbstract());
        assertFalse(visitor.isFinal());

        assertEquals("com.roamer.litespring.service.v4.PetStoreService", visitor.getClassName());
        assertEquals("java.lang.Object", visitor.getSuperClassName());
        assertEquals(0, visitor.getInterfaceNames().length);
    }
}
