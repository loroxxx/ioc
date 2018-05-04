package com.luo;

import com.luo.bean.BeanDefine;

import java.util.List;
import java.util.Set;

public interface Ioc {

    /**
     添加对象
     */
    void addBean(Object bean);

    /**
     根据名称添加对象
     */
    void addBean(String name, Object bean);

    /**
     根据类添加对象
     */
    <T> T addBean(Class<T> type);

    /**
     根据类重新设置对象
     */
    void setBean(Class<?> type, Object proxyBean);

    /**
        根据名称获取类
     */
    Object getBean(String name);

    /**
        根据类获取类
     */
    <T> T getBean(Class<T> type);

    /**
    获取容器中所有BeanDefine
     */
    List<BeanDefine> getBeanDefines();

    /**
    根据类获取容器中的BeanDefine
     */
    BeanDefine getBeanDefine(Class<?> type);

    /**
     获取容器中所有对象
     */
    List<Object> getBeans();

    /**
    获取容器中所有对象的名称
     */
    Set<String> getBeanNames();

    /**
    根据类移除容器中的对象
     */
    void remove(Class<?> type);

    /**
    根据对象名称移除容器中的对象
     */
    void remove(String beanName);

    /**
    清除容器
     */
    void clearAll();

}