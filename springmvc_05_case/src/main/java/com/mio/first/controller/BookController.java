package com.mio.first.controller;

import com.mio.first.pojo.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    //创建
    @PostMapping
    public String save(@RequestBody Book book){ //从请求体 获取数据，要加注解
        System.out.println("[POST]books save..."+book);
        return "[POST]books.save()"+book;
    }
    //查询信息：全部
    @GetMapping
    public List<Book> getAll(){
        List<Book> bookList=new ArrayList<>();

        Book book1=new Book();
        book1.setType("计算机");
        book1.setName("Java 基础");
        book1.setPrice(12);
        bookList.add(book1);

        Book book2=new Book();
        book2.setType("计算机");
        book2.setName("Spring 基础");
        book2.setPrice(58);
        bookList.add(book2);

        System.out.println("[GET]books getAll..."+bookList);
        return bookList;
    }



    //删除:路径参数
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        System.out.println("[DELETE]books delete..."+id);
        return "[DELETE]books.delete()"+id;
    }
    //修改:传入新对象
    @PutMapping
    public String update(@RequestBody Book book){
        System.out.println("[PUT]books update..."+book);
        return "[PUT]books.update()"+book;
    }
    //查询信息：指定id
    @GetMapping("/{id}")
    public String getOne(@PathVariable int id){
        System.out.println("[GET]books getOne..."+id);
        return "[GET]books.getOne()"+id;
    }

}
