package com.zxf.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 总配置类
 */
@Configuration
@ComponentScan(basePackages = {"com.zxf.config", "com.zxf.bean"})
public class StartConfigrantion {
}
