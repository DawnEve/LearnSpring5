package com.mio.mapper;

import com.mio.pojo.Brand;

import java.util.List;

public interface BrandMapper {

    List<Brand> selectAll();

    Brand selectById(int id);
}
