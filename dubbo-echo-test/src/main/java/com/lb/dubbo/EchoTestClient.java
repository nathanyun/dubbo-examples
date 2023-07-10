package com.lb.dubbo;

import org.apache.dubbo.rpc.service.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class EchoTestClient {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();

        //指定获取 EchoService, 所有dubbo接口自动实现此接口, 用于回声测试
        EchoService service = (EchoService) context.getBean("greetingService");
        //远程调用
        Object result = service.$echo("echo");
        System.out.println("EchoService.$echo result ===> " + result);
        //阻塞主线程
        LockSupport.park();
    }
}
