package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@DubboService(executor = "greetingServiceExecutor", version = "1.0.0", group = "Group1")
public class GreetingServiceImpl implements GreetingService {
    @Override
    public CompletableFuture<String> sayHi(String name) {
        String threadName = Thread.currentThread().getName();
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(threadName +" - Hi, "+ name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return String.format("Hi, %s",name);
        });
    }
}
