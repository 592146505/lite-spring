package com.roamer.litespring.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 构造器注入描述
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/8 17:37
 */
public class ConstructorArgument {

    private List<ValueHolder> valueHolders = new ArrayList<>();

    /**
     * 添加构造器注入参数
     *
     * @param valueHolder
     */
    public void addValueHolder(ValueHolder valueHolder) {
        valueHolders.add(valueHolder);
    }

    /**
     * 获取构造器注入参数个数
     *
     * @return
     */
    public List<ValueHolder> getValueHolders() {
        return Collections.unmodifiableList(valueHolders);
    }

    /**
     * 构造器注入参数是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return valueHolders.isEmpty();
    }

    /**
     * 清理构造器注入参数
     */
    public void clear() {
        valueHolders.clear();
    }

    /**
     * 获取构造器注入参数个数
     *
     * @return
     */
    public int getArgumentCount() {
        return valueHolders.size();
    }

    /**
     * 每个ValueHolder都是一个构造器注入参数定义
     */
    public static class ValueHolder {
        private String name;
        private String type;
        /**
         * 引用类型或字符型
         */
        private Object value;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
