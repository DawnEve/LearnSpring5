package com.mio.first.controller;

import com.mio.first.pojo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// RESTful 入门案例
@Controller
public class BookController {

    //保存
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseBody
    public String save(){
        System.out.println("[POST]books save...");
        return "[POST]books.save()";
    }

    //删除:路径参数
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable int id){
        System.out.println("[DELETE]books delete..."+id);
        return "[DELETE]books.delete()"+id;
    }

    //修改:传入新对象
    @RequestMapping(value = "/books", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody Book book){
        System.out.println("[PUT]books update..."+book);
        return "[PUT]books.update()"+book;
    }

    //查询信息：指定id
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getOne(@PathVariable int id){
        System.out.println("[GET]books getOne..."+id);
        return "[GET]books.getOne()"+id;
    }

    //查询信息：全部
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseBody
    public String getAll(){
        System.out.println("[GET]books getAll...");
        return "[GET]books.getAll()";
    }

}
