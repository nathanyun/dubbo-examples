package com.lb.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务提供者
 * @EnableDubbo是个复合注解,用于启动Dubbo,包括{@code @EnableDubboConfig和@DubboComponentScan}
 */
@EnableDubbo
@SpringBootApplication
public class MergeSpringBootProvider {

    public static void main(String[] args) {
        SpringApplication.run(MergeSpringBootProvider.class, args);
        System.out.println("MergeSpringBootProvider started");
    }
}
