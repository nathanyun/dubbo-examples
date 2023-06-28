package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.OrderService;

import java.util.concurrent.CompletableFuture;

public class OrderServiceImpl implements OrderService {
    @Override
    public CompletableFuture<String> create(String productName) {
        String threadName = Thread.currentThread().getName();
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(threadName +" - OrderServiceImpl create productName = " + productName);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return String.format("productName :%s created!", productName);
        });
    }
}
