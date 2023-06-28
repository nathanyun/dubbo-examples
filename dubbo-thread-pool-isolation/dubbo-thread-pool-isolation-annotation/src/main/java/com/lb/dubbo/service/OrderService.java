package com.lb.dubbo.service;

import java.util.concurrent.CompletableFuture;


public interface OrderService {

    /**
     * CompletableFuture接口
     * @param productName
     * @return
     */
    CompletableFuture<String> create(String productName);
}
