package com.lb.springboot.service.impl;

import com.lb.springboot.service.GreetingService;

import java.util.concurrent.TimeUnit;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        System.out.println("Hi ===> " + name);
        return String.format("Hi, %s ! Power by Dubbo Spring XML", name);
    }
}
