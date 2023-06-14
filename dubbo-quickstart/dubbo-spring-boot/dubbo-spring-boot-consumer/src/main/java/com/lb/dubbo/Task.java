package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class Task implements CommandLineRunner {

    /**
     *  @DubboReference从Dubbo获取一个RPC接口订阅
     */
    @DubboReference
    private GreetingService greetingService;

    @Override
    public void run(String... args) throws Exception {
        String result = greetingService.sayHi("world");
        System.out.println("Receive result ======> " + result);

        //每隔1秒调用一次
        new Thread(()-> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(new Date() + " Receive result ======> " + greetingService.sayHi("world"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}