package top.cuizilin.dby;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.cuizilin.dby.mapper.TagMapper;

import java.util.Collections;

@SpringBootTest
class DbyDocumentApplicationTests {

    @Autowired
    private TagMapper tagMapper;

    @Test
    public void test1(){
        System.out.println(tagMapper.searchALlByBookId("1"));
    }

    @Test
    void contextLoads() {
//                FastAutoGenerator.create("jdbc:mysql://localhost:3306/dbybook", "root", "333333")
//                .globalConfig(builder -> {
//                    builder.author("shardemachael") // 设置作者
//                            .outputDir("D:\\SOFT\\temp\\dby_document\\src\\main\\java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("top.cuizilin.dby") .entity("pojo")// 设置父包名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\SOFT\\temp\\dby_document\\src\\main\\resources\\mappers")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("t_user").addInclude("t_tag").addInclude("t_book") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
    }

}
