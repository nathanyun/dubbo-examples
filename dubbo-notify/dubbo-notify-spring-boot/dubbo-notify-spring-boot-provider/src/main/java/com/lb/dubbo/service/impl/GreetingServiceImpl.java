package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @DubboService 指定为Dubbo服务
 */
@DubboService
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        System.out.println("name = " + name);
        if (name.endsWith("2")){
            throw new IllegalArgumentException("mock error");
        }
        return String.format("Hi, %s",name);
    }
}
