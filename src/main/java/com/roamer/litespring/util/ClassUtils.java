package com.roamer.litespring.util;


import java.util.HashMap;
import java.util.Map;

/**
 * Class工具类
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:18
 */
public abstract class ClassUtils {

    /**
     * The package separator character: '.'
     */
    private static final char PACKAGE_SEPARATOR = '.';

    /**
     * The path separator character: '/'
     */
    private static final char PATH_SEPARATOR = '/';

    /**
     * Map with primitive wrapper type as key and corresponding primitive
     * type as value, for example: Integer.class -> int.class.
     */
    private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE_TYPE_MAP = new HashMap<>(8);

    /**
     * Map with primitive type as key and corresponding wrapper
     * type as value, for example: int.class -> Integer.class.
     */
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TYPE_TO_WRAPPER_MAP = new HashMap<>(8);

    static {
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Boolean.class, boolean.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Byte.class, byte.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Character.class, char.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Double.class, double.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Float.class, float.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Integer.class, int.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Long.class, long.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Short.class, short.class);

        for (Map.Entry<Class<?>, Class<?>> entry : WRAPPER_TO_PRIMITIVE_TYPE_MAP.entrySet()) {
            PRIMITIVE_TYPE_TO_WRAPPER_MAP.put(entry.getValue(), entry.getKey());
        }

    }

    /**
     * 获取 Default ClassLoader
     *
     * @return 一个可用的ClassLoader
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            //获取所处上下文类加载器
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            //获取当前类加载器
            cl = ClassUtils.class.getClassLoader();
            if (cl == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    //获取Boot类加载器
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }

    /**
     * value是否可转换为指定类型
     *
     * @param type  需要转换的类型
     * @param value 值
     * @return
     */
    public static boolean isAssignableValue(Class<?> type, Object value) {
        Assert.notNull(type, "Type must not be null");
        return (value != null ? isAssignable(type, value.getClass()) : !type.isPrimitive());
    }

    public static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
        Assert.notNull(lhsType, "Left-hand side type must not be null");
        Assert.notNull(rhsType, "Right-hand side type must not be null");
        // isAssignableFrom: 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // 子类可直接转换为超类类型
        if (lhsType.isAssignableFrom(rhsType)) {
            return true;
        }
        // isPrimitive: 判定该 Class 对象是否表示一个基本类型。
        if (lhsType.isPrimitive()) {
            // 基本类型需要转为对应的包装类型
            Class<?> resolvedPrimitive = WRAPPER_TO_PRIMITIVE_TYPE_MAP.get(rhsType);
            if (resolvedPrimitive != null && lhsType.equals(resolvedPrimitive)) {
                return true;
            }
        } else {
            // 对应包装类转为对应基本类型
            Class<?> resolvedWrapper = PRIMITIVE_TYPE_TO_WRAPPER_MAP.get(rhsType);
            if (resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将资源路径转换为class路径
     *
     * @param resourcePath 资源路径 以.分割
     * @return class路径 以/分隔
     */
    public static String convertResourcePathToClassName(String resourcePath) {
        Assert.notNull(resourcePath, "Resource path must not be null");
        return resourcePath.replace(PATH_SEPARATOR, PACKAGE_SEPARATOR);
    }

    /**
     * 将class路径转换为资源路径
     *
     * @param className class路径 以/分隔
     * @return 资源路径 以.分割
     */
    public static String convertClassNameToResourcePath(String className) {
        Assert.notNull(className, "Class name must not be null");
        return className.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }
}
