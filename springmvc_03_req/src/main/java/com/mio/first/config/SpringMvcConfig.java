package com.mio.first.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration //配置类，代替配置文件
@ComponentScan("com.mio.first") //扫描路径
@EnableWebMvc //开启mvc模式，可以处理json
public class SpringMvcConfig {
}
