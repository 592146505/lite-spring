package com.roamer.litespring.context.annotation;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.factory.BeanDefinitionStoreException;
import com.roamer.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.roamer.litespring.beans.factory.support.BeanNameGenerator;
import com.roamer.litespring.core.io.Resource;
import com.roamer.litespring.core.io.support.PackageResourceLoader;
import com.roamer.litespring.core.type.AnnotationMetadata;
import com.roamer.litespring.core.type.classreading.MetadataReader;
import com.roamer.litespring.core.type.classreading.SimpleMetadataReader;
import com.roamer.litespring.stereotype.Component;
import com.roamer.litespring.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 类路径下扫描定义BeanDefinition
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/28 10:48
 */
public class ClassPathBeanDefinitionScanner {

    private static final Log logger = LogFactory.getLog(ClassPathBeanDefinitionScanner.class);

    private final BeanDefinitionRegistry registry;

    private final PackageResourceLoader resourceLoader = new PackageResourceLoader();

    private final BeanNameGenerator generator = new AnnotationBeanNameGenerator();

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 执行扫描
     *
     * @param packagesToScan 包名，多个路径以 , 号分割
     */
    public void doScan(String packagesToScan) {
        // 将包路径以 , 号为分割符，转换为数组形式
        String[] basePackages = StringUtils.tokenizeToStringArray(packagesToScan, ",");

        // 逐个包进行扫描
        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();
        for (String basePackage : basePackages) {
            // 获取包下所有使用@Component注解的class，生成BeanDefinition
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            // 注册BeanDefinition
            for (BeanDefinition candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registerBeanDefinition(candidate.getId(), candidate);
            }
        }
    }

    private Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();

        try {
            // 扫描包下所有class，生成Resource
            Resource[] resources = resourceLoader.getResources(basePackage);

            // 找到使用@Component注解的class，生成BeanDefinition
            for (Resource resource : resources) {
                try {
                    // 解析class文件,获取元数据
                    MetadataReader reader = new SimpleMetadataReader(resource);
                    AnnotationMetadata metadata = reader.getAnnotationMetadata();
                    // 如果使用了@Component注解，则创建ScannerGenericBeanDefinition
                    if (metadata.hasAnnotation(Component.class.getName())) {
                        ScannerGenericBeanDefinition sbd = new ScannerGenericBeanDefinition(metadata);
                        // 使用名称生成器
                        String beanName = generator.generateBeanName(sbd, registry);
                        sbd.setId(beanName);
                        candidates.add(sbd);
                    }
                } catch (Throwable e) {
                    throw new BeanDefinitionStoreException(
                            "Failed to read candidate component class: " + resource, e);
                }
            }
        } catch (IOException e) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", e);
        }
        return candidates;
    }
}
