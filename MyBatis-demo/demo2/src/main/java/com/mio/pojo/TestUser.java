package com.mio.pojo;

import com.mio.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// MyBatis 的代理开发
public class TestUser {
    public static void main(String[] args) throws IOException {
        System.out.println("test02: ");
        //https://mybatis.org/mybatis-3/getting-started.html
        //1.加载核心配置文件，获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取 SqlSession 对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.1 获取 UserMapper 接口的代理方法
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //3.2 执行方法
        List<User> users = mapper.selectAll();
        System.out.println(users);

        User user=mapper.selectById(2); //带参数的方法
        System.out.println(user);

        //4. 释放资源
        sqlSession.close();
    }
}
