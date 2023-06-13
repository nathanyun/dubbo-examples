package com.lb.springboot.service.impl;

import com.lb.springboot.service.GreetingService;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        return String.format("V3.0 Hello, %s ! power by Dubbo Annotation", name);
    }
}
