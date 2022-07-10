package top.cuizilin.dby.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cuizilin.dby.service.IBookService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private IBookService bookService;

    @GetMapping("/scan")
    public String scan() throws Exception{
       return JSON.toJSONString(bookService.scan()) ;
    }
}
