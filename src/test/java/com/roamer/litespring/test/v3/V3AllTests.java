package com.roamer.litespring.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * V3版集中测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/10 20:15
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTest.class,
        BeanDefinitionTest.class,
        ConstructorResolverTest.class
})
public class V3AllTests {
}
