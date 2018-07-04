package com.roamer.litespring.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * v2版集中测试类
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/21 19:55
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTest.class,
        BeanDefinitionTest.class,
        BeanDefinitionResolverTest.class,
        CustomNumberEditorTest.class,
        CustomBooleanEditorTest.class
})
public class V2AllTests {
}
