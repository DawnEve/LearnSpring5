package com.mio.first.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ServeletInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //应对中文乱码：get和post参数
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter=new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        //return super.getServletFilters();
        return new Filter[]{filter};  //多个过滤器还可以继续往后写
    }
}


/*
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
*/