package com.mio.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/save")
    @ResponseBody
    public String save(){
        System.out.println("user save...");
        return "{'msg': 'spring5 mvc, from user'}";
    }


//    private String msg="<h2>hello, ajax from file</h2>";
    private String msg;

    @RequestMapping("/ajax")
    @ResponseBody
    public String ajax() {
        System.out.println("show ajax ...");
        String path=this.getClass().getResource("/").getPath();
        System.out.println("路径一为："+ path);

        // read from file;
        try {
            FileReader reader = new FileReader(path+"ajax.html");
            BufferedReader bufferedReader=new BufferedReader(reader);
            StringBuffer sb=new StringBuffer();
            String s="";
            while( (s=bufferedReader.readLine() )!=null ){
                sb.append(s+"\n");
            }
            bufferedReader.close();
            reader.close();
            msg=sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.msg;
    }


    @RequestMapping(value = "/user", params = "user_id")
    public String getUser(int user_id){
        return String.format("User[id=%d]", user_id);
    }
}
