package top.cuizilin.dby;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import top.cuizilin.dby.dto.R;
import top.cuizilin.dby.mapper.BookMapper;
import top.cuizilin.dby.mapper.TagMapper;
import top.cuizilin.dby.pojo.Book;
import top.cuizilin.dby.pojo.Booklist;
import top.cuizilin.dby.service.IBookService;
import top.cuizilin.dby.service.IBooklistService;
import top.cuizilin.dby.service.ITagService;
import top.cuizilin.dby.service.IUserService;
import top.cuizilin.dby.utils.PDFUtil;

import java.io.*;
import java.util.*;

@org.springframework.boot.test.context.SpringBootTest
class SpringBootNormalTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private IBookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private TagMapper tagMapper;


    @Autowired
    private IBooklistService booklistService;

    @Test
    public void test2() {
        Booklist booklist = booklistService.getOne(new QueryWrapper<Booklist>().eq("id", "1522562555908820994"));
        System.out.println(booklist);

    }


    @Test
    void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/dbybook", "root", "333333")
                .globalConfig(builder -> {
                    builder.author("shardemachael") // 设置作者
                            .outputDir("D:\\SOFT\\temp\\dby_document\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("top.cuizilin.dby").entity("pojo")// 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\SOFT\\temp\\dby_document\\src\\main\\resources\\mappers")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_booklist") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    public void test3() {
        redisTemplate.opsForValue().set("ss", new byte[]{0, 2, 3, 4, 5});
        String s = (String) redisTemplate.opsForValue().get("ss");
        System.out.println(s.getBytes().length);
    }

    @Test
    public void test4() throws Exception {
        String path = Thread.currentThread().getContextClassLoader().getResource(".").getPath();

        FileInputStream in = new FileInputStream(path + "info.json");

    }

}
