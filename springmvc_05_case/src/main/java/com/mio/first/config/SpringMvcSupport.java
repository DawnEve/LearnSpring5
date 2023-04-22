package com.mio.first.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration //1.这是配置类，2.要加入扫描路径
public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //当访问pages/??时，走pages/下，而不是让 SpringMVC处理。
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        //其他静态资源访问不到时，也逐个放行
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/plugin/**").addResourceLocations("/plugin/");

        //super.addResourceHandlers(registry);
    }
}
