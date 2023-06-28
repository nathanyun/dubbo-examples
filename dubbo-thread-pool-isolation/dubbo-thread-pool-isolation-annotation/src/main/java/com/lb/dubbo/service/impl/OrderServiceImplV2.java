package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.OrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@DubboService(version = "2.0.0", group = "Group3")
public class OrderServiceImplV2 implements OrderService {
    @Override
    public CompletableFuture<String> create(String productName) {
        String threadName = Thread.currentThread().getName();
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(threadName +" - OrderServiceImplV2 create productName = " + productName);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return String.format("productName :%s created!", productName);
        });
    }
}
