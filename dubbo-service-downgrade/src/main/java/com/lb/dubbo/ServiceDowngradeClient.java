package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class ServiceDowngradeClient {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();

        //指定获取 stub 服务
        GreetingService service = (GreetingService) context.getBean("greetingService");
        //指定获取简单的 mock 服务
        //GreetingService service = (GreetingService) context.getBean("greetingService1");
        //指定获取自定义 mock 服务
        //GreetingService service = (GreetingService) context.getBean("greetingService2");

        //远程调用
        String message = service.sayHi("dubbo");
        System.out.println("Receive message ===> " + message);
        //阻塞主线程
        LockSupport.park();
    }
}
