package com.itheima;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = { "com.itheima.controller" })

@SpringBootApplication
//@EnableAutoConfiguration
@MapperScan("com.itheima.mapper")
public class Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run( Application.class,args );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
