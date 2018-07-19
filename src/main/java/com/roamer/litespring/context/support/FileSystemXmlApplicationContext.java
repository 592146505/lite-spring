package com.roamer.litespring.context.support;

import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.core.io.FileSystemResource;

/**
 * 文件系统XML Bean容器
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/25 14:27
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
