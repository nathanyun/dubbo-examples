package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class ServiceDowngradeClient {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();

        GreetingService service = context.getBean(GreetingService.class);

        //每2秒调用1次
        while (true){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ignored) { }

            String message = service.sayHi("dubbo");
            System.out.println("Receive message ===> " + message);
        }
        //LockSupport.park();
    }
}
