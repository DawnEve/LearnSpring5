package com.mio.test;

import com.mio.mapper.BrandMapper;
import com.mio.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    @Test
    public void testSelectAll() throws IOException {
        //1. 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3 获取Mapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.执行对应的接口方法: 整合spring后，只有这一行了，其他都是流程化的，都可以框架实现。
        // 个人重点写的就是接口方法名，和对应xml中的sql语句。
        List<Brand> brands = mapper.selectAll();
        System.out.println(brands);

        Brand brand = mapper.selectById(2);
        System.out.println(brand);

        //5.释放资源
        sqlSession.close();
    }
}
