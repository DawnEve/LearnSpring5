package com.mio.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @RequestMapping("/save")
    @ResponseBody
    public String save(){
        System.out.println("user save...");
        return "{'msg': 'spring5 mvc'}";
    }

    @RequestMapping(value = "/user", params = "user_id")
    public String getUser(int user_id){
        return String.format("User[id=%d]", user_id);
    }
}
