package com.luo.bean;

import lombok.Builder;
import lombok.Data;

import java.lang.annotation.Annotation;

@Data
@Builder
public class Scanner {

    private String                      packageName;//解析的包名
    private boolean                     recursive; //是否递归
    private Class<?>                    parent;//
    private Class<? extends Annotation> annotation;


}