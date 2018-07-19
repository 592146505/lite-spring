package com.roamer.litespring.beans.factory.annotation;

/**
 * 标记 自动注入
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/15 14:59
 */
public @interface Autowired {

    /**
     * 所以依赖的bean是否必须存在
     *
     * @return 默认为true
     */
    boolean required() default true;
}
