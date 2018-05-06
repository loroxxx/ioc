package com.luo;


import com.luo.bean.ClassInfo;
import com.luo.bean.Scanner;

import java.util.Set;

/**
 * 读取类的接口
 */
public interface ClassReader {

    Set<ClassInfo> readClasses(Scanner scanner);

}