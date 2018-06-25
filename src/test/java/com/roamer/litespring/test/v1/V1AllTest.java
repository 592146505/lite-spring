package com.roamer.litespring.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * v1版集中测试类
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/21 19:55
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeanFactoryTest.class,
        ApplicationContextTest.class,
        ResourceTest.class,
})
public class V1AllTest {
}
