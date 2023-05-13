package com.mio.test;

import com.mio.mapper.UserMapper;
import com.mio.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MyBatisUserTest {

    @Test
    public void testSelect() throws IOException {
        //接收的参数
        String username="zhangsan";
        String password="123";

        //1. 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取 SqlSession 对象
        //加参数true表示自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3 获取Mapper接口的代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4.执行对应的接口方法: 整合spring后，只有这一行了，其他都是流程化的，都可以框架实现。
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("查询对象");
        User user= mapper.select(username, password);

        //手动提交事务
        //sqlSession.commit();
        System.out.println(user);

        //5.释放资源
        sqlSession.close();
    }


    @Test
    public void testSelectUserById() throws IOException {
        //接收的参数
        int id=2;

        //1. 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取 SqlSession 对象
        //加参数true表示自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3 获取Mapper接口的代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4.执行对应的接口方法: 整合spring后，只有这一行了，其他都是流程化的，都可以框架实现。
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("查询对象");
        User user= mapper.selectById(id);

        //手动提交事务
        //sqlSession.commit();
        System.out.println(user);

        //5.释放资源
        sqlSession.close();
    }


    @Test
    public void testSelect2() throws IOException {
        //接收的参数
        Collection collection= new HashSet();
        collection.add("Lily");
        collection.add("zhangsan");

        //
        List list=new LinkedList();
        list.add("Lily");
        list.add("zhangsan");

        //
        String[] arr=new String[2];
        arr[0]="Lily";
        arr[1]="zhangsan";

        //1. 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取 SqlSession 对象
        //加参数true表示自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3 获取Mapper接口的代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4.执行对应的接口方法: 整合spring后，只有这一行了，其他都是流程化的，都可以框架实现。
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("查询对象: input list");
        //List<User> users= mapper.select2(collection);
        //List<User> users= mapper.select2(list);
        List<User> users= mapper.select2(arr);

        //手动提交事务
        //sqlSession.commit();
        System.out.println(users);

        //5.释放资源
        sqlSession.close();
    }


    @Test
    public void testSelectByMap() throws IOException {
        //接收的参数
        HashMap hashMap=new HashMap();
//        hashMap.put("username", "Lily");
//        hashMap.put("password", "234");
        User user0=new User();
        user0.setUsername("Lily");
        user0.setPassword("234");
        hashMap.put("map", user0);

        //1. 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取 SqlSession 对象
        //加参数true表示自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3 获取Mapper接口的代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4.执行对应的接口方法: 整合spring后，只有这一行了，其他都是流程化的，都可以框架实现。
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("查询对象: input map");
        User user= mapper.selectByMap(hashMap);

        //手动提交事务
        //sqlSession.commit();
        System.out.println(user);

        //5.释放资源
        sqlSession.close();
    }
}
