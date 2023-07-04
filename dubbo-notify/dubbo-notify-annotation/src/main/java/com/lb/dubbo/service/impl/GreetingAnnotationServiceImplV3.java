package com.lb.dubbo.service.impl;

import com.lb.dubbo.configuration.ProviderConfiguration;
import com.lb.dubbo.service.GreetingAnnotationService;

/**
 * 基于Java配置注解方式的Dubbo接口定义
 * {@link ProviderConfiguration#greetingAnnotationService()}
 */
public class GreetingAnnotationServiceImplV3 implements GreetingAnnotationService {
    @Override
    public String sayHi(String name) {
        return String.format("V3.0 Hello, %s ! power by Dubbo Annotation", name);
    }
}
