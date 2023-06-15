package com.lb.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务提供者
 */
@EnableDubbo
@SpringBootApplication
public class ClusterFaultToleranceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClusterFaultToleranceProviderApplication.class, args);
    }
}
