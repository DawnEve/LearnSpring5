package com.mio.mapper;

import com.mio.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll(); //返回值类型：一个用User，很多个用List<User>

    User selectById(int id);
}
