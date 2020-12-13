package com.zxf.bean;

import lombok.Data;

@Data
public abstract class People {

    public People(){
        System.out.println("人类基类创建!");
    }

    private String tall;

    private Double weight;

}
