package com.zxf.bean;

import lombok.Data;

@Data
public class Teacher extends People {

    public Teacher(){
        System.out.println("Teacher 创建");
    }

    private String id;
    
}
