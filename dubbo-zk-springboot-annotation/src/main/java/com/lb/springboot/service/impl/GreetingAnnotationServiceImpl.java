package com.lb.springboot.service.impl;

import com.lb.springboot.service.GreetingAnnotationService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 基于Annotation注解方式的Dubbo接口定义
 *
 * 指定registry="zk-registry" 注册中心的ID [通过注解将 service 关联到上文定义的特定注册中心]
 */
@DubboService(version = "v1.0", group = "qa")
public class GreetingAnnotationServiceImpl implements GreetingAnnotationService {
    @Override
    public String sayHi(String name) {
        return String.format("Hello, %s ! power by Dubbo Annotation", name);
    }
}
