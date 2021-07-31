package com.zxf.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试循环依赖
 * @author zhuxiaofeng
 * @date 2021/7/31
 */
@Data
@Component
public class AAA {

    @Autowired
    private Teacher teacher;

    @Autowired
    private BBB bbb;

}
