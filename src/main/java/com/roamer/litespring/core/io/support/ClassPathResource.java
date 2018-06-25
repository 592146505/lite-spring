package com.roamer.litespring.core.io.support;

import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 类路径下资源
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/21 20:21
 */
public class ClassPathResource implements Resource {

    private String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader != null ?
                classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // 通过classLoader获取资源输入流
        InputStream is = this.classLoader.getResourceAsStream(this.path);
        if (is == null) {
            throw new FileNotFoundException(this.path + " cannot be opened");
        }
        return is;
    }

    @Override
    public String getDescription() {
        return this.path;
    }

}
