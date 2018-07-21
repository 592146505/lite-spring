package com.roamer.litespring.core.type;

/**
 * class元数据
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/19 23:21
 */
public interface ClassMetadata {

    /**
     * 获取类全限定名
     *
     * @return {package}.{className}
     */
    String getClassName();

    /**
     * 是否是接口
     *
     * @return 是:true 不是:false
     */
    boolean isInterface();

    /**
     * 是否是抽象类
     *
     * @return 是:true 不是:false
     */
    boolean isAbstract();

    /**
     * 是否是final修饰
     *
     * @return 是:true 不是:false
     */
    boolean isFinal();

    /**
     * 是否有父类
     *
     * @return 有:true 没有:false
     */
    boolean hasSuperClass();

    /**
     * 获取父类名称
     *
     * @return {package}.{superClassName}
     */
    String getSuperClassName();

    /**
     * 获取实现接口名称
     *
     * @return [{package}.{interfaceName},{package}.{interfaceName}]
     */
    String[] getInterfaceNames();
}
