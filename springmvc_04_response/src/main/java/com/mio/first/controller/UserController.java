package com.mio.first.controller;

import com.mio.first.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    //响应pojo集合对象
    @RequestMapping("/save4")
    @ResponseBody
    public List<User> save4() {
        System.out.print("return pojo List: ");

        User user=new User();
        user.setName("James");
        user.setAge(20);

        User user2=new User();
        user2.setName("Lily");
        user2.setAge(21);

        List<User> userList=new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        System.out.println(userList);

        return userList;
    }

    //返回pojo对象
    @RequestMapping("/save3")
    @ResponseBody //必须加这一句话才能响应json，pojo类必须有setter和getter!!!
    public User save3() {
        System.out.print("return pojo: ");

        User user=new User();
        user.setName("James");
        user.setAge(20);
        System.out.println(user);
        return user;
    }

    //返回页面
    @RequestMapping("/toPage")
    public String toPage() {
        System.out.println("user.toPage ...");
        return "index"; //写成index.jsp 报错: 文.件[/WEB-INF/views/index.jsp.jsp] 未找到
    }

    @RequestMapping(value = "/save2", produces = "application/text;charset=utf-8")
    @ResponseBody
    public String save2(String name) {
        System.out.println("user save2 ..."+name);
        return String.format("name:%s", name);
    }
    @RequestMapping("/save")
    public String save() {
        System.out.println("user save ...");
        return "index";
    }
}
