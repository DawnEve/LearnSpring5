package com.mio.first.controller;

import com.mio.first.domain.Author;
import com.mio.first.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    //接收集合
    @RequestMapping("/list")
    @ResponseBody
    public String getList(@RequestParam List<String> likes){
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
