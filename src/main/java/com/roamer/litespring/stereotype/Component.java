package com.roamer.litespring.stereotype;

import java.lang.annotation.*;

/**
 * 标注组件
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/15 14:43
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    /**
     * 自定义组件名称
     *
     * @return 组件名称
     */
    String value() default "";
}
