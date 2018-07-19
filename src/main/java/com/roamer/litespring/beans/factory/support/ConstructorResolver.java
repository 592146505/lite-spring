package com.roamer.litespring.beans.factory.support;

import com.roamer.litespring.beans.BeanDefinition;
import com.roamer.litespring.beans.ConstructorArgument;
import com.roamer.litespring.beans.SimpleTypeConverter;
import com.roamer.litespring.beans.TypeConverter;
import com.roamer.litespring.beans.factory.BeanCreationException;
import com.roamer.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Bean构造函数注入 解析器
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/10 20:27
 */
public class ConstructorResolver {

    private static final Log logger = LogFactory.getLog(ConstructorResolver.class);

    private final ConfigurableBeanFactory beanFactory;

    public ConstructorResolver(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 自动匹配构造函数注入获取bean实例
     *
     * @param bd bean定义
     * @return bean实例
     */
    public Object autowireConstructor(BeanDefinition bd) {

        // 可使用的构造函数
        Constructor constructorToUse = null;
        // 可注入的参数
        Object[] argsToUse = null;

        // 如果加载过，则直接获取
        Class beanClass = bd.getBeanClass();
        if (beanClass == null) {
            try {
                beanClass = beanFactory.getClassLoader().loadClass(bd.getBeanClassName());
                // 加载过后，放入缓存
                bd.setBeanClass(beanClass);
            } catch (ClassNotFoundException e) {
                throw new BeanCreationException(bd.getId(), "Instantiation of bean failed, can't resolve class", e);
            }
        }

        // 获取beanClass所有公共构造方法
        Constructor[] candidates = beanClass.getConstructors();
        // 获取beanDefinition中的定义的构造参数
        ConstructorArgument argument = bd.getConstructorArgument();

        // 创建BeanDefinition 值 解析器
        BeanDefinitionValueResolver bdResolver = new BeanDefinitionValueResolver(beanFactory);
        // 创建类型转换器
        TypeConverter typeConverter = new SimpleTypeConverter();
        // 进行匹配
        for (Constructor candidate : candidates) {
            // 获取构造函数参数类型
            Class[] parameterTypes = candidate.getParameterTypes();
            // 排除个数不匹配
            if (parameterTypes.length != argument.getArgumentCount()) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];

            // 注入参数类型与构造函数是否匹配
            boolean result = valueMatchTypes(
                    parameterTypes,
                    argument.getValueHolders(),
                    argsToUse,
                    bdResolver,
                    typeConverter);

            if (result) {
                constructorToUse = candidate;
            }
        }

        // 未匹配到合适的构造函数抛出异常
        if (constructorToUse == null) {
            throw new BeanCreationException(bd.getId(), "can't find a appropriate constructor");
        }
        // 使用构造函数创建实例
        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(bd.getId(), "can't find a create instance using " + constructorToUse);
        }

    }

    /**
     * 注入参数类型与构造函数是否匹配
     *
     * @param parameterTypes 构造函数参数类型
     * @param valueHolders   注入参数定义
     * @param argsToUse      真正可注入的参数
     * @param bdResolver     BeanDefinition 值 解析器
     * @param typeConverter  类型转换器
     * @return 匹配true, 不匹配false
     */
    private boolean valueMatchTypes(Class[] parameterTypes, List<ConstructorArgument.ValueHolder> valueHolders,
                                    Object[] argsToUse, BeanDefinitionValueResolver bdResolver, TypeConverter typeConverter) {
        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);
            // 引用类型/字符型等
            Object value = valueHolder.getValue();
            try {
                // 解析为bean实例或字符等
                Object resolvedValue = bdResolver.resolveValueIfNecessary(value);
                // 进行类型转换
                argsToUse[i] = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
            } catch (Exception e) {
                logger.error(e);
                return false;
            }
        }
        return true;
    }

}
