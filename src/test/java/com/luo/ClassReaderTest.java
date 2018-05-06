package com.luo;

import com.luo.annotation.Bean;
import com.luo.bean.ClassInfo;
import com.luo.bean.Scanner;
import com.luo.reader.AbstractClassReader;
import com.luo.reader.ClassPathClassReader;
import com.luo.reader.JarReaderImpl;
import org.junit.Test;

import java.util.Set;

public class ClassReaderTest {

    @Test
    public void classPathReaderTest1() {

        AbstractClassReader reader = new ClassPathClassReader();

        Scanner scanner = Scanner.builder().packageName("com").recursive(true).annotation(Bean.class).build();
        Set<ClassInfo> classInfos = reader.readClasses(scanner);
        for (ClassInfo classInfo : classInfos) {
            System.out.println(classInfo.getClassName());
        }

    }

    @Test
    public void classPathReaderTest2() {

        AbstractClassReader reader = new ClassPathClassReader();

        Scanner scanner = Scanner.builder().packageName("com").recursive(true).parent(Injector.class).build();
        Set<ClassInfo> classInfos = reader.readClasses(scanner);
        for (ClassInfo classInfo : classInfos) {
            System.out.println(classInfo.getClassName());
        }

    }

    @Test
    public void jarReaderTest() {
        AbstractClassReader jarReader=new JarReaderImpl();
        Scanner scanner = Scanner.builder().packageName("org").recursive(true).build();
        Set<ClassInfo> classInfos =jarReader.readClasses(scanner);
        for (ClassInfo classInfo : classInfos) {
            System.out.println(classInfo.getClassName());
        }
    }

}
