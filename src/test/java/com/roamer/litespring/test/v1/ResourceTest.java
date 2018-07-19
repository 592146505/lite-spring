package com.roamer.litespring.test.v1;

import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.core.io.ClassPathResource;
import com.roamer.litespring.core.io.FileSystemResource;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * 资源接口测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/21 20:05
 */
public class ResourceTest {

    @Test
    public void classPathResource() throws Exception {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        InputStream is = null;
        try {
            is = resource.getInputStream();
            assertNotNull(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }

    }

    @Test
    public void fileSystemResource() throws Exception {
        // 测试时应保证测试可以跨越空间和时间
        Resource resource = new FileSystemResource("src\\test\\resources\\petstore-v1.xml");
        InputStream is = null;
        try {
            is = resource.getInputStream();
            assertNotNull(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }

    }

}
