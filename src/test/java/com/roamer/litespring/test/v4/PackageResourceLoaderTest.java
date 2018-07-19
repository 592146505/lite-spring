package com.roamer.litespring.test.v4;

import com.roamer.litespring.core.io.Resource;

import com.roamer.litespring.core.io.support.PackageResourceLoader;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * 包资源扫描测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/15 15:11
 */
public class PackageResourceLoaderTest {


    @Test
    public void testGetResources() throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("com.roamer.litespring.dao.v4");

        assertEquals(2, resources.length);
    }

}
