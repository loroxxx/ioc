package com.luo.bean;


import lombok.Data;

@Data
public class BeanDefine {

    private Object bean;
    private Class<?> type;
    private boolean isSingle; //是否单例

    public BeanDefine(Object bean) {
        this(bean, bean.getClass());
    }

    public BeanDefine(Object bean, Class<?> type) {
        this.bean = bean;
        this.type = type;
        this.isSingle = true;
    }

    public BeanDefine(Object bean, Class<?> type, boolean isSingle) {
        this.bean = bean;
        this.type = type;
        this.isSingle = isSingle;
    }

}