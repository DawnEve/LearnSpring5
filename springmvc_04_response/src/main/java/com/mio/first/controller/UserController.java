package com.mio.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/save2")
    @ResponseBody
    public String save2(String name) {
        System.out.println("user save2 ..."+name);
        return String.format("{name:%s}", name);
    }
    @RequestMapping("/save")
    public String save() {
        System.out.println("user save ...");
        return "index";
    }
}
