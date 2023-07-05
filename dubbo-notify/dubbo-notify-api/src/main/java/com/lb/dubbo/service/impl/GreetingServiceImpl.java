package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.GreetingService;

import java.time.LocalDateTime;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        if (name.endsWith("2")){
            throw new IllegalArgumentException("mock error");
        }
        System.out.println(LocalDateTime.now() + " Hi ===> " + name);
        return String.format("Hi, %s ! Power by Dubbo Spring XML", name);
    }
}
