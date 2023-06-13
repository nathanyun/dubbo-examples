package com.lb.springboot.service.impl;

import com.lb.springboot.service.GreetingService;

import java.util.concurrent.TimeUnit;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) { }
        return String.format("Hello, %s ! power by Dubbo Bootstrap", name);
    }
}
