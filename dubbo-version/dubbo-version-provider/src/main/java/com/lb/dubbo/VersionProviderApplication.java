package com.lb.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 服务提供者
 * @EnableDubbo是个复合注解,用于启动Dubbo,包括{@code @EnableDubboConfig和@DubboComponentScan}
 */
@EnableDubbo
@SpringBootApplication
public class VersionProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(VersionProviderApplication.class, args);
    }
}
