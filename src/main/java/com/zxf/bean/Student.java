package com.zxf.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Student {

    private String name;

    private Integer age;

}
