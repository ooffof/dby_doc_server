package top.cuizilin.dby.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import top.cuizilin.dby.service.ITagService;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private ITagService tagService;

    @GetMapping("/add")
    public String add(String tagName){
        return JSON.toJSONString(tagService.add(tagName));
    }

    @GetMapping("/list")
    public String list(){
        return JSON.toJSONString(tagService.listTag());
    }

    @PostMapping("/info")
    public String getInfo(Boolean flag, @RequestParam(required = false) String info) throws IOException {
        return JSON.toJSONString(tagService.getInfo(flag, info));
    }
}
