package com.roamer.litespring.core.io;

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

    private final String path;

    private final ClassLoader classLoader;

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
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(path + " cannot be opened");
        }
        return is;
    }

    @Override
    public String getDescription() {
        return path;
    }

}
