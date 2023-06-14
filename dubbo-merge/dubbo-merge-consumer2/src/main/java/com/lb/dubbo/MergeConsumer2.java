package com.lb.dubbo;

import com.lb.dubbo.service.MergeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 消费端应用
 */
public class MergeConsumer2 {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();
        //每2秒调用1次
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(LocalDateTime.now() + " MergeConsumer2 Receive results ======> " + context.getBean(MergeService.class).mergeResult());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
