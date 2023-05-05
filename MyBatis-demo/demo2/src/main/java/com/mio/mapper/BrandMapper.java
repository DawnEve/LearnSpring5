package com.mio.mapper;

import com.mio.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {

    List<Brand> selectAll();
    Brand selectById(int id);

    //多条件查询 三种写法，任选一个
    /*
    //写法1：散装参数，分别列举三个参数，使用注解@Param("xml中的占位符")指定xml中哪个占位符接收该参数
    List<Brand> selectByCondition(@Param("status") int status,
                                  @Param("companyName") String companyName,
                                  @Param("brandName") String brandName);
    //写法2：xml中的占位符要和类中的属性名对应
    List<Brand> selectByCondition(Brand brand);
    */
    //写法3：xml中的占位符要和map中的key对应
    List<Brand> selectByCondition(Map map);
    /*
    */

    //单条件动态查询
    List<Brand> selectByConditionSingle(Brand brand);

    //添加数据
    void add(Brand brand);
    void add2(Brand brand);

    //修改数据
    int update(Brand brand);
    int updateDyn(Brand brand); //动态sql

    //删除
    void deleteById(int id);

//    void deleteByIds(@Param("ids") int[] ids);
    void deleteByIds(int[] ids);
    //不写注解，则xml中要使用 collection="array"，否则报错 Parameter 'ids' not found
}
