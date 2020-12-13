package com.zxf.context;

import com.zxf.bean.Student;
import com.zxf.config.StartConfigrantion;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring容器启动类
 */
public class SpringStarter {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(StartConfigrantion.class);
        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(student);
    }

}
