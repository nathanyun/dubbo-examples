package com.lb.springboot.service.impl;

import com.lb.springboot.service.GreetingService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        System.out.println(LocalDateTime.now() + " Hi ===> " + name);
        return String.format("Hi, %s ! Power by Dubbo Spring XML", name);
    }
}
