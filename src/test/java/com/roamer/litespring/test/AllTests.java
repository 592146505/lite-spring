package com.roamer.litespring.test;

import com.roamer.litespring.test.v1.V1AllTests;
import com.roamer.litespring.test.v2.V2AllTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 集中测试
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/3 17:19
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        V1AllTests.class,
        V2AllTests.class
})
public class AllTests {
}
