package com.mio.first.controller;

import com.mio.first.domain.Author;
import com.mio.first.domain.Book;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    //传入日期+时间 2088/08/28 9:30:50
    @RequestMapping("/dataParam3")
    @ResponseBody
    public String dataParam3(@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") Date date){
        System.out.println("date: "+date);
        return "{'module': 'date param'}";
    }


    //传入日期 2088-08-28
    @RequestMapping("/dataParam2")
    @ResponseBody
    public String dataParam2(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        System.out.println("date: "+date);
        return "{'module': 'date param'}";
    }

    //传入日期 2088/08/28
    @RequestMapping("/dataParam")
    @ResponseBody
    public String dataParam(Date date){
        System.out.println("date: "+date);
        return "{'module': 'date param'}";
    }

    //参数是json，包含多个对象，接收到集合框架中
    @RequestMapping("/save7")
    @ResponseBody
    public String save7(@RequestBody List<Book> list){ //参数前加注解
        System.out.println(list);
        return String.format("{'books': '%s'}", list);
    }


    //参数是json，接收为对象
    @RequestMapping("/save6")
    @ResponseBody
    public String save6(@RequestBody Book book){ //参数前加注解
        System.out.println(book);
        return String.format("{'book': '%s'}", book);
    }


    //接收json
    @RequestMapping("/list")
    @ResponseBody
    public String getList(@RequestBody List<String> likes){
        System.out.println(likes);
        return String.format("{'likes': '%s'}", likes);
    }

    //接收集合
    @RequestMapping("/list2")
    @ResponseBody
    public String getList2(@RequestParam List<String> likes){
        System.out.println(likes);
        return String.format("{'likes': '%s'}", likes);
    }

    //接收数组
    @RequestMapping("/array")
    @ResponseBody
    public String getArray(String[] likes){
        System.out.println(Arrays.toString(likes));
        return String.format("{'likes': '%s'}", Arrays.toString(likes));
    }


    @RequestMapping("/save5")
    @ResponseBody
    public String save5(Book book){
        System.out.println(book);
        return String.format("{'book': '%s'}", book);
    }

    @RequestMapping("/save4")
    @ResponseBody
    public String save4(@RequestParam("bookname") String name, int price){
        System.out.println("book name: "+name);
        System.out.println("book price: "+price);
        return String.format("{'book': '%s', 'price': %d}", name, price);
    }

    @RequestMapping("/save3")
    @ResponseBody
    public String save3(String name, int price){
        System.out.println("book name: "+name);
        System.out.println("book price: "+price);
        return String.format("{'book': '%s', 'price': %d}", name, price);
    }


    @RequestMapping("/save2")
    @ResponseBody
    public String save2(){
        System.out.println("book save...");
        return "{'msg': 'spring5 mvc, Book'}";
    }
}
