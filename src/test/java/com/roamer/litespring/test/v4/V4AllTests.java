package com.roamer.litespring.test.v4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * V4版集中测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/10 20:15
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        PackageResourceLoaderTest.class,
        ClassReaderTest.class,
        MetadataReaderTest.class,
        ClassPathBeanDefinitionScannerTest.class,
        XmlBeanDefinitionReaderTest.class
})
public class V4AllTests {
}
