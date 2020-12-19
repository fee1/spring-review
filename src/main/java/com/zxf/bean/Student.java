package com.zxf.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Student extends People  {

    public Student(){
        System.out.println("Student 类创建！");
    }

    private String name;

    private Integer age;

}
