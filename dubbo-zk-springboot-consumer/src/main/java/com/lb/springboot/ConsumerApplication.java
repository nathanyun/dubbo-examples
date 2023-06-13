package com.lb.springboot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消费端应用
 */
@EnableDubbo
@SpringBootApplication
public class ConsumerApplication {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }


//    /**
//     * 不通过注册中心调用DUBBO付服务，可指定URL
//     * 生产环境不推荐使用
//     */
//    @DubboReference(version = "1.0",url = "dubbo://127.0.0.1:10190")
//    private GreetingService greetingService;
//
//    /**
//     * 通过ZK注册中心访问DUBBO服务
//     */
//    @DubboReference(version = "1.0",registry = "${dubbo.registry.address}")
//    private UserService userService;
//
//
//    @Bean
//    public ApplicationRunner greetingTest() {
//        return args -> logger.info("通过指定URL调用DUBBO服务-SayHi：{}",greetingService.sayHi("yunnasheng"));
//
//    }
//
//    @Bean
//    public ApplicationRunner getUserTest() {
//        return args -> logger.info("通过注册中心调用DUBBO服务-getUser：{}",userService.get(UUID.randomUUID().toString()));
//    }
}
