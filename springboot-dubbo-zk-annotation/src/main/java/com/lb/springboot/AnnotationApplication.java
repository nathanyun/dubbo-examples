package com.lb.springboot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务提供者
 * @EnableDubbo 注解必须配置，否则将无法加载 Dubbo 注解定义的服务，@EnableDubbo 可以定义在主类上
 *
 * Spring Boot 注解默认只会扫描 main 类所在的 package，
 * 如果服务定义在其它 package 中，需要增加配置 EnableDubbo(scanBasePackages = {"org.apache.dubbo.springboot.demo.provider"})
 */
@EnableDubbo
@SpringBootApplication
public class AnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnotationApplication.class, args);
    }
}
