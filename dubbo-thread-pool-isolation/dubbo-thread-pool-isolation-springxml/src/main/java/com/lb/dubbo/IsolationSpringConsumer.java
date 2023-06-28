package com.lb.dubbo;

import com.lb.dubbo.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IsolationSpringConsumer {

    public static void main(String[] args)  {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();

        OrderService orderService = (OrderService) context.getBean("orderService");
        //OrderService orderService = (OrderService) context.getBean("orderServiceV2");
        orderService.create("HUAWEI CLOUD ECS");
        //LockSupport.park();
    }

}
