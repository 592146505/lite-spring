package com.roamer.litespring.core.io;

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

    private final String path;
    private final File file;

    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        file = new File(path);
    }

    public FileSystemResource(File file) {
        Assert.notNull(file, "File must not be null");
        this.file = file;
        path = file.getPath();
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public String getDescription() {
        return "file [" + file.getAbsolutePath() + "]";
    }
}
