package com.mio.first.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //配置类，代替配置文件
@ComponentScan("com.mio.first") //扫描路径
public class SpringMvcConfig {
}
