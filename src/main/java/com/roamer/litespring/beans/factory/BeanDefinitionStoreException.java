package com.roamer.litespring.beans.factory;


import com.roamer.litespring.beans.BeansException;

/**
 * XML解析封装BeanDefinitionStore异常
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 15:02
 */
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
