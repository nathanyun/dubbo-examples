package com.lb.dubbo.service;

import java.util.concurrent.CompletableFuture;


public interface GreetingService {

    /**
     * CompletableFuture接口
     * @param name
     * @return
     */
    CompletableFuture<String> sayHi(String name);

    /**
     * 普通接口, 使用AsyncContext实现异步
     * @param name
     * @return
     */
    String sayHello(String name);
}
