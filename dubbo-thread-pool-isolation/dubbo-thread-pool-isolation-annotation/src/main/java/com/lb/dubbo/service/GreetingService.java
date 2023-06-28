package com.lb.dubbo.service;

import java.util.concurrent.CompletableFuture;


public interface GreetingService {

    /**
     * CompletableFuture接口
     * @param name
     * @return
     */
    CompletableFuture<String> sayHi(String name);
}
