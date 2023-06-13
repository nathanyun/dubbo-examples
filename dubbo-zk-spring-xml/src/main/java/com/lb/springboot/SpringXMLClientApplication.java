package com.lb.springboot;

import com.lb.springboot.service.GreetingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class SpringXMLClientApplication {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();

        GreetingService service = context.getBean(GreetingService.class);

        String message = service.sayHi("dubbo");
        System.out.println("Receive message ===> " + message);

        //阻塞主线程
        LockSupport.park();
    }
}
