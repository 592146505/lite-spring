package com.roamer.litespring.context.support;

import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.core.io.support.ClassPathResource;

/**
 * 类路径XML Bean容器
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/21 10:12
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return new ClassPathResource(path, getClassLoader());
    }

}
