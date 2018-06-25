package com.roamer.litespring.core.io.support;

import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.util.Assert;

import java.io.*;

/**
 * 文件系统下资源
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/21 20:23
 */
public class FileSystemResource implements Resource {

    private File file;

    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must not be null");
        this.file = new File(path);
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }
}
