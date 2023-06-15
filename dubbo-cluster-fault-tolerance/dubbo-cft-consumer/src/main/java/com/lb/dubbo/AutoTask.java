package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Deprecated
//@Component
public class AutoTask implements CommandLineRunner {

    /**
     *  从Dubbo获取一个RPC接口订阅
     *  version = "*" 不区分版本
     */
    @DubboReference(version = "*")
    private GreetingService greetingService;

    @Override
    public void run(String... args) throws Exception {
        //每隔2秒调用一次
        new Thread(()-> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(new Date() + " Receive result ======> " + greetingService.sayHi("Dubbo 集群容错", 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}