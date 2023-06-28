package com.lb.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务提供者
 */
@EnableDubbo
@SpringBootApplication(scanBasePackages = "com.lb.dubbo")
public class IsolationAnnotationApplication {


    /**
     * 这里仅是服务提供者, 可以利用其他消费端进行调用测试
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(IsolationAnnotationApplication.class, args);
    }
}
