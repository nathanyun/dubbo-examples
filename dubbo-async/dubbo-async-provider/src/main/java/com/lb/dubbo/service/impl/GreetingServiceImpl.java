package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.GreetingService;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        System.out.println(LocalDateTime.now() + " Hi ===> " + name);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return String.format("Hi, %s ! Power by Service Downgrade Server", name);
    }
}
