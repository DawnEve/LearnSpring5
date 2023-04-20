package com.mio.first.controller;

import com.mio.first.domain.Author;
import com.mio.first.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/book")
public class BookController {

    @RequestMapping("/save")
    @ResponseBody
    public String save(Book book){
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
