package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public CompletableFuture<String> sayHi(String name) {
        //通过 return CompletableFuture.supplyAsync() ，业务执行已从 Dubbo 线程切换到业务线程，避免了对 Dubbo 线程池的阻塞。
        return CompletableFuture.supplyAsync(() -> {
            String message = String.format("Hi, %s ! Power by Async CompletableFuture", name);
            System.out.println(message);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return message;
        });
    }

    @Override
    public String sayHello(String name) {
        AsyncContext asyncContext = RpcContext.startAsync();

        new Thread(()->{
            //异步上下文签名开关
            asyncContext.signalContextSwitch();

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 写入异步结果
            asyncContext.write(String.format("Hi, %s ! Power by AsyncContext", name));
        }).start();

        //同步返回null
        return null;
    }
}
