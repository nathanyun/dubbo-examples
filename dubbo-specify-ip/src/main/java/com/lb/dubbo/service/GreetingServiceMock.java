package com.lb.dubbo.service;


public class GreetingServiceMock implements GreetingService{

    @Override
    public String sayHi(String name) {
        System.out.printf("About to execute mock: [%s] \n", GreetingServiceMock.class.getSimpleName());
        return String.format("Hi, %s ! Power by Consumer Service Downgrade Mock", name);
    }
}
