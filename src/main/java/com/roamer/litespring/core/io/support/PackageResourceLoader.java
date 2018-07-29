package com.roamer.litespring.core.io.support;

import com.roamer.litespring.core.io.FileSystemResource;
import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.util.Assert;
import com.roamer.litespring.util.ClassUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 包资源扫描器
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/15 15:17
 */
public class PackageResourceLoader {

    private static final Log logger = LogFactory.getLog(PackageResourceLoader.class);

    private final ClassLoader classLoader;

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public PackageResourceLoader(ClassLoader classLoader) {
        Assert.notNull(classLoader, "ResourceLoader must not be null");
        this.classLoader = classLoader;
    }

    /**
     * 扫描指定包下（包含子包）的类
     *
     * @param basePackage 需扫描的包名
     * @return 类资源（Resource）
     */
    public Resource[] getResources(String basePackage) throws IOException {
        Assert.notNull(basePackage, "basePackage must not be null");
        // 转换为资源路径的形式
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        // 生成File对象
        URL url = classLoader.getResource(location);
        File rootDir = new File(url.getFile());

        // 获取目录下文件
        Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
        // 整合为资源
        Resource[] result = new Resource[matchingFiles.size()];
        int i = 0;
        for (File file : matchingFiles) {
            result[i++] = new FileSystemResource(file);
        }
        return result;
    }

    /**
     * 检索文件夹下所有文件
     *
     * @param rootDir 扫描根路径
     * @return 目录下所有文件
     * @throws IOException 读取文件异常时
     */
    protected Set<File> retrieveMatchingFiles(File rootDir) throws IOException {
        if (!rootDir.exists()) {
            // 目录不存在
            if (logger.isDebugEnabled()) {
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it does not exists");
            }
            return Collections.emptySet();
        }
        if (!rootDir.isDirectory()) {
            // 非目录
            if (logger.isWarnEnabled()) {
                logger.warn("Skipping [" + rootDir.getAbsolutePath() + "] because it does not denote a directory");
            }
            return Collections.emptySet();
        }
        if (!rootDir.canRead()) {
            // 不可读
            if (logger.isWarnEnabled()) {
                logger.warn("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() +
                        "] because the application is not allowed to read the directory");
            }
            return Collections.emptySet();
        }

        // 扫描目录下所有文件
        Set<File> resultFiles = new LinkedHashSet<>(8);
        doRetrieveMatchingFile(rootDir, resultFiles);
        return resultFiles;
    }

    /**
     * 递归寻找目录下所有文件
     *
     * @param directory   扫描路径
     * @param resultFiles 目录下所有文件集
     * @throws IOException 读取文件异常时
     */
    protected void doRetrieveMatchingFile(File directory, Set<File> resultFiles) throws IOException {

        // 获取该目录下所有文件（包括文件夹）
        File[] dirContents = directory.listFiles();
        if (dirContents == null) {
            // 未检索到子文件
            if (logger.isWarnEnabled()) {
                logger.warn("Could not retrieve contents of directory [" + directory.getAbsolutePath() + "]");
            }
            return;
        }

        // 遍历获取所有文件
        for (File content : dirContents) {
            if (content.isDirectory()) {
                // 文件夹
                if (!content.canRead()) {
                    // 不可读跳过
                    logger.debug("Skipping subdirectory [" + content.getAbsolutePath() +
                            "] because the application is not allowed to read the directory");
                } else {
                    // 递归查找
                    doRetrieveMatchingFile(content, resultFiles);
                }
            } else {
                // 文件
                resultFiles.add(content);
            }
        }
    }


    public ClassLoader getClassLoader() {
        return classLoader;
    }
}
