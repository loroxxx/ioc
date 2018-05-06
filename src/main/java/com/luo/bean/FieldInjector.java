package com.luo.bean;

import com.luo.Injector;
import com.luo.Ioc;

import java.lang.reflect.Field;

public class FieldInjector implements Injector {

    private Ioc ioc;
    private Field field;

    public FieldInjector(Ioc ioc, Field field) {
        this.ioc = ioc;
        this.field = field;
    }

    @Override
    public void injection(Object bean) {
        try {
            Class<?> fieldType = field.getType();
            Object   value     = ioc.getBean(fieldType);
            if (value == null) {
                throw new IllegalStateException("Can't inject bean: " + fieldType.getName() + " for field: " + field);
            }
            field.setAccessible(true);
            field.set(bean, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}