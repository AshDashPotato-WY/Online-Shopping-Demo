package org.example.onlineshoppingdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.onlineshoppingdemo.db.mappers")
public class OnlineShoppingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingDemoApplication.class, args);
    }

}
