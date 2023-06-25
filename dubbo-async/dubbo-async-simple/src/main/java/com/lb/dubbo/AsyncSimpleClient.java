package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncSimpleClient {

    /**
     * 更多案例请移步{@linkplain  com.lb.dubbo.AsyncSimpleAppTest}
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();

        //直接调用服务
        GreetingService service = context.getBean(GreetingService.class);
        CompletableFuture<String> future = service.sayHi("Jack");

        //future.get()会阻塞等待响应结果
        System.out.println(future.get());
    }
}
