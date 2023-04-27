package com.mio;

import net.ipip.ipdb.City;
import net.ipip.ipdb.IPFormatException;

import java.io.IOException;
import java.util.Arrays;

public class CityUtil {
    private static City city_DB;

    public static String[] find(String ip, String lang){
        String path=new CityUtil().getClass().getResource("/").getPath();
        System.out.println("path=" + path);
        try {
            if(null == city_DB){
                city_DB = new City(path + "ipipfree.ipdb");
                System.out.println(city_DB);
            }
            return city_DB.find(ip, lang);
        } catch (IOException | IPFormatException e) {
            throw new RuntimeException(e);
        }
    }

    // 注意：这里只能使用jdk8。使用jdk17会报错
    public static void main(String[] args) {
        //只有最后一个ip起作用
        String ip="58.62.28.25"; //[中国, 广东, 广州]
        ip="39.156.66.10"; // ping baidu.com  [中国, 北京, 北京]
        ip="202.196.64.35"; //www.zzu.edu.cn (202.196.64.35) [中国, 河南, 郑州]

        ip="172.18.1.3"; // [局域网, 局域网, ]
        ip="116.6.234.147"; // sust [中国, 广东, 深圳]

        //开始查询
        String[] results = CityUtil.find(ip, "CN");
        //输出结果
        System.out.println(ip + " @ " + Arrays.toString(results));
    }
}
