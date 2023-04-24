package com.mio.pojo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// MyBatis 快速入门
public class TestUser {
    public static void main(String[] args) throws IOException {
        //https://mybatis.org/mybatis-3/getting-started.html
        //1.加载核心配置文件，获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.执行SQL语句
        List<User> users = sqlSession.selectList("com.mio.UserMapper.selectAll");
        System.out.println(users);

        //4.释放资源
        sqlSession.close();

    }
}
