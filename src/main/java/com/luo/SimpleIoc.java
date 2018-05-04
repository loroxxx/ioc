package com.luo;

import com.luo.bean.BeanDefine;


import java.util.*;

public class SimpleIoc implements Ioc {

    private final Map<String, BeanDefine> pool = new HashMap(32);


    public void addBean(Object bean) {
        addBean(bean.getClass().getName(), bean);
    }

    public void addBean(String name, Object bean) {

        //把bean包装成BeanDefine放入容器
        BeanDefine beanDefine = new BeanDefine(bean);
        addBeanDefine(name, beanDefine);

        Class<?>[] interfaces = beanDefine.getType().getInterfaces();
        if (interfaces.length > 0) {
            for (Class<?> interfaceClazz : interfaces) {
                this.addBeanDefine(interfaceClazz.getName(), beanDefine);
            }
        }

    }

    public <T> T addBean(Class<T> type) {
        Object bean = addBeanDefine(type, true);
        return type.cast(bean);
    }

    public void setBean(Class<?> type, Object proxyBean) {
        BeanDefine beanDefine = pool.get(type.getName());
        if (beanDefine != null) {
            beanDefine.setBean(proxyBean);
        } else {
            beanDefine = new BeanDefine(proxyBean, type);
        }
        pool.put(type.getName(), beanDefine);
    }

    public Object getBean(String name) {
        BeanDefine beanDefine = pool.get(name);
        if (beanDefine == null) {
            return null;
        }
        return beanDefine.getBean();
    }

    public <T> T getBean(Class<T> type) {
        Object bean = this.getBean(type.getName());
        try {
            return type.cast(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BeanDefine> getBeanDefines() {
        return new ArrayList<>(pool.values());
    }

    public BeanDefine getBeanDefine(Class<?> type) {
        return this.getBeanDefine(type, true);
    }

    public List<Object> getBeans() {
        Set<String>  beanNames = this.getBeanNames();
        List<Object> beans     = new ArrayList<>(beanNames.size());
        for (String beanName : beanNames) {
            Object bean = this.getBean(beanName);
            if (null != bean) {
                beans.add(bean);
            }
        }
        return beans;
    }

    public Set<String> getBeanNames() {
        return pool.keySet();
    }

    public void remove(Class<?> type) {
        pool.remove(type.getSimpleName());
    }

    public void remove(String beanName) {
        pool.remove(beanName);
    }

    public void clearAll() {
        pool.clear();
    }



    private void addBeanDefine(String name, BeanDefine beanDefine) {
        if (pool.put(name, beanDefine) != null) {
//            log.warn("Duplicated Bean: {}", name);

        }
    }


    private Object addBeanDefine(Class<?> type, boolean singleton) {
        return addBeanDefine(type.getName(), type, singleton);
    }


    private Object addBeanDefine(String name, Class<?> beanClass, boolean singleton) {
        BeanDefine beanDefine = this.getBeanDefine(beanClass, singleton);

        if (pool.put(name, beanDefine) != null) {
//            log.warn("Duplicated Bean: {}", name);

        }

        // add interface
        Class<?>[] interfaces = beanClass.getInterfaces();
        if (interfaces.length > 0) {
            for (Class<?> interfaceClazz : interfaces) {
                if (null != this.getBean(interfaceClazz)) {
                    break;
                }
                this.addBean(interfaceClazz.getName(), beanDefine);
            }
        }

        return beanDefine.getBean();
    }

    private BeanDefine getBeanDefine(Class<?> beanClass, boolean singleton) {
        try {
            Object object = beanClass.newInstance();
            return new BeanDefine(object, beanClass, singleton);
        } catch (InstantiationException | IllegalAccessException e) {

        }
        return null;
    }


    public static void main(String[] args) {
        SimpleIoc ioc=new SimpleIoc();
        ioc.addBean("ioc",new SimpleIoc());
        SimpleIoc bean = (SimpleIoc)ioc.getBean("ioc");
        Ioc bean1 = ioc.getBean(Ioc.class);
        System.out.println(bean);
        System.out.println(bean1);
    }
}
