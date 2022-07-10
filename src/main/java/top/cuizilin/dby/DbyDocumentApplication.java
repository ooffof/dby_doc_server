package top.cuizilin.dby;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.cuizilin.dby.mapper")
public class DbyDocumentApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbyDocumentApplication.class, args);
    }

}
