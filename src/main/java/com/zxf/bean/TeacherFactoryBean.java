package com.zxf.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * bean注入工厂类实例
 */
@Component
public class TeacherFactoryBean implements FactoryBean<Teacher> {

    /**
     * 创建bean
     * @return
     * @throws Exception
     */
    @Override
    public Teacher getObject() throws Exception {
        return new Teacher();
    }

    /**
     * 返回bean的类型
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Teacher.class;
    }

    /**
     * 这个bean是否是单例的，jdk1.8以后的抽象类都可以存在默认方法。
     * @return boolean true(默认) 单例bean false 不是单例
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
