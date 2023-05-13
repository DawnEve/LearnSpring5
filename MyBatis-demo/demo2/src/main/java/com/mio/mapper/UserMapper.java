package com.mio.mapper;

import com.mio.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface UserMapper {

    List<User> selectAll(); //返回值类型：一个用User，很多个用List<User>

    User selectById(int id);

    /*
    MyBatis 参数封装
    * 单个参数：
        1.POJO类性
        2.Map集合
        3.Collection
        4.List
        5.Array
        6.其他类型
    * 多个参数
    */
    User select(@Param("username") String username, @Param("password") String password);
    List<User> select2(@Param("usernames") Collection collection);
//    List<User> select2(@Param("usernames") List list);
    List<User> select2(@Param("mylist") List mylist);
    List<User> select2(@Param("myarr") String[] myarr);
//    User select(String username, String password);
    User selectByMap(@Param("mymap") HashMap map);
}
