package com.mio.first.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class ServeletInitConfig extends AbstractDispatcherServletInitializer {
    //加载的配置对象，一旦tomcat启动，就加载。可在tomcat中访问spring mvc信息
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context=
                new AnnotationConfigWebApplicationContext();
        //注册 配置类
        context.register(SpringMvcConfig.class);
        return context;
    }

    //设置spring 管理的范围，其余归tomcat处理
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; //所有请求都归Spring处理
    }

    //加载Spring MVC之外的上下文容器信息。可以忽略
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
