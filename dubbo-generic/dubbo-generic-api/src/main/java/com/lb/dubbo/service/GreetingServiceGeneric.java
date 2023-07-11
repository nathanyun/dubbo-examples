package com.lb.dubbo.service;

import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.concurrent.CompletableFuture;

/**
 * 提供者泛化调用实现
 */
public class GreetingServiceGeneric implements GenericService {
    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        System.out.println("$invoke = " + method);
        if ("sayHello".equals(method)) {
            return "Welcome " + args[0];
        }
        return null;
    }

    @Override
    public CompletableFuture<Object> $invokeAsync(String method, String[] parameterTypes, Object[] args) throws GenericException {
        return GenericService.super.$invokeAsync(method, parameterTypes, args);
    }
}
