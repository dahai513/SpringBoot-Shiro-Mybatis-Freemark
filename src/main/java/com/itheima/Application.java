package com.itheima;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Rabbitmq自动配置
 * 1.RabbitAutoConfiguration
 * 2.有自动配置了连接工厂CachingConnectionFactory
 * 3.RabbitProperties：封装了rabbitmq的配置
 * 4.RabbitTemplate： 给rabbitmq发送和接收消息
 * 5.AmqpAdmin： rabbitmq系统管理功能组件
 *      AmqpAdmin 创建和删除 queue exchange binding
 * 6.@EnableRabbit+@RabbitListener监听消息队列
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.itheima.mapper")
public class Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run( Application.class, args );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

