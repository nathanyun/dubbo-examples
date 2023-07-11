package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class AccesslogClient {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();

        GreetingService greetingService = context.getBean(GreetingService.class);
        //远程调用
        greetingService.sayHi("testing");
        //阻塞主线程
        LockSupport.park();
    }
}
