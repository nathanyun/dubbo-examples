package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.GreetingAnnotationService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 基于Annotation注解方式的Dubbo接口定义
 * @Service 注解从 3.0 版本开始就已经废弃，改用 @DubboService，以区别于 Spring 的 @Service 注解
 */
@DubboService(version = "v2.0", group = "dev", timeout = 5000)
public class GreetingAnnotationServiceImplV2 implements GreetingAnnotationService {
    @Override
    public String sayHi(String name) {
        return String.format("V2.0 Hello, %s ! power by Dubbo Annotation", name);
    }
}
