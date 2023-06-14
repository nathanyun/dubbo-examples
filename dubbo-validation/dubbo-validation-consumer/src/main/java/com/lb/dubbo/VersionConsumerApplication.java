package com.lb.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消费端应用
 */
@EnableDubbo
@SpringBootApplication
public class VersionConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VersionConsumerApplication.class);
    }
}
