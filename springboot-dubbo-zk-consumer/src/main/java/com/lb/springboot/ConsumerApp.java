package com.lb.springboot;

import com.lb.springboot.service.GreetingService;
import com.lb.springboot.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

/**
 * Hello world!
 */
@EnableAutoConfiguration
public class ConsumerApp {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 不通过注册中心调用DUBBO付服务，可指定URL
     * 生产环境不推荐使用
     */
    @DubboReference(version = "1.0",url = "dubbo://127.0.0.1:10190")
    private GreetingService greetingService;

    /**
     * 通过ZK注册中心访问DUBBO服务
     */
    @DubboReference(version = "1.0",registry = "${dubbo.registry.address}")
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class).close();
    }

    @Bean
    public ApplicationRunner greetingTest() {
        return args -> logger.info("通过指定URL调用DUBBO服务-SayHi：{}",greetingService.sayHi("yunnasheng"));

    }

    @Bean
    public ApplicationRunner getUserTest() {
        return args -> logger.info("通过注册中心调用DUBBO服务-getUser：{}",userService.get(UUID.randomUUID().toString()));
    }
}
