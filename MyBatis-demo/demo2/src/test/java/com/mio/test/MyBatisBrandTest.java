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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisBrandTest {
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

    @Test
    public void testSelectByCondition() throws IOException {
        //接收的参数
        int status=1;
        String companyName="技术";
        String brandName="为";
        //处理参数
        companyName = "%"+companyName+"%";
        brandName = "%"+brandName+"%";


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
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("写法3");

        /*
        // 写法1 散装参数
        List<Brand> brands = mapper.selectByCondition(status, companyName, brandName);

        // 写法2 封装对象
        Brand brand=new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        List<Brand> brands = mapper.selectByCondition(brand);
        */

        // 写法3 集合对象
        Map map=new HashMap();
        //map.put("status", status);
        map.put("companyName", companyName);
        map.put("brandName", brandName);
        List<Brand> brands = mapper.selectByCondition(map);


        System.out.println(brands);

        //5.释放资源
        sqlSession.close();
    }




    @Test
    public void testSelectByConditionSingle() throws IOException {
        //接收的参数
        int status=1;
        String companyName="技术";
        String brandName="为";
        //处理参数
        companyName = "%"+companyName+"%";
        brandName = "%"+brandName+"%";


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
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("单条件动态查询，只能有一个where语句，哪一个不确定");

        // 封装对象
        Brand brand=new Brand();
        //brand.setStatus(status);
        brand.setCompanyName(companyName);
        //brand.setBrandName(brandName);
        List<Brand> brands = mapper.selectByConditionSingle(brand);

        System.out.println(brands);

        //5.释放资源
        sqlSession.close();
    }



    @Test
    public void testAdd() throws IOException {
        //接收的参数
        int status=1;
        String companyName="波导手机";
        String brandName="波导";
        String description="手机中的战斗机";
        int ordered=100;

        //1. 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //加参数true表示自动提交事务

        //3 获取Mapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.执行对应的接口方法: 整合spring后，只有这一行了，其他都是流程化的，都可以框架实现。
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("添加对象");

        // 封装对象
        Brand brand=new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        //mapper.add(brand); //添加
        //System.out.println(brand);
        //System.out.println(brand.getId()); //获取id失败，这里是一个null

        mapper.add2(brand); //添加，并获取主键
        System.out.println(brand);
        System.out.println(brand.getId()); //获取主键id成功

        //手动提交事务
        //sqlSession.commit();

        //5.释放资源
        sqlSession.close();
    }



    @Test
    public void testUpdate() throws IOException {
        //接收的参数
        int status=1;
        String companyName="魅族手机";
        String brandName="魅族";
        String description="小而美";
        int ordered=10;
        int id=15;

        //1. 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //加参数true表示自动提交事务

        //3 获取Mapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.执行对应的接口方法: 整合spring后，只有这一行了，其他都是流程化的，都可以框架实现。
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("修改对象");

        // 封装对象
        Brand brand=new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);
        brand.setId(id);

        int result = mapper.update(brand); //修改
        System.out.println(brand);
        System.out.println("result=" + result);

        //手动提交事务
        //sqlSession.commit();

        //5.释放资源
        sqlSession.close();
    }



    @Test
    public void testUpdateDyn() throws IOException {
        //接收的参数
        int status=0;
        String companyName="金刚商店";
        String brandName="金刚";
        String description="村里最好的产品";
        int ordered=8;
        int id=16;

        //1. 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //加参数true表示自动提交事务

        //3 获取Mapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.执行对应的接口方法: 整合spring后，只有这一行了，其他都是流程化的，都可以框架实现。
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("修改对象");

        // 封装对象
        Brand brand=new Brand();
        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
//        brand.setDescription(description);
        brand.setOrdered(ordered);
        brand.setId(id);

        int result = mapper.updateDyn(brand); //修改
        System.out.println(brand);
        System.out.println("result=" + result);

        //手动提交事务
        //sqlSession.commit();

        //5.释放资源
        sqlSession.close();
    }



    @Test
    public void testDeleteById() throws IOException {
        //接收的参数
        int id=4;

        //1. 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //加参数true表示自动提交事务

        //3 获取Mapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.执行对应的接口方法: 整合spring后，只有这一行了，其他都是流程化的，都可以框架实现。
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("修改对象");


        mapper.deleteById(id); //删除

        //手动提交事务
        //sqlSession.commit();

        //5.释放资源
        sqlSession.close();
    }



    @Test
    public void testDeleteByIds() throws IOException {
        //接收的参数
        int[] ids={2, 15, 16};

        //1. 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //加参数true表示自动提交事务

        //3 获取Mapper接口的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.执行对应的接口方法: 整合spring后，只有这一行了，其他都是流程化的，都可以框架实现。
        // 重点写的就是接口方法名，和对应xml中的sql语句。
        System.out.println("修改对象");


        mapper.deleteByIds(ids); //删除

        //手动提交事务
        //sqlSession.commit();

        //5.释放资源
        sqlSession.close();
    }
}
