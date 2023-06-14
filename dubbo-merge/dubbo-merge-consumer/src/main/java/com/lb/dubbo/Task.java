package com.lb.dubbo;

import com.lb.dubbo.service.MergeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class Task implements CommandLineRunner {

    /**
     * 合并所有接口:group = "*" ,merger = "true"
     * 合并merge2,merge3:group = "merge2,merge3" ,merger = "true"
     *  @DubboReference从Dubbo获取一个RPC接口订阅
     */
    @DubboReference(group = "merge2,merge3" ,merger = "true")
    private MergeService mergeService;

    @Override
    public void run(String... args) throws Exception {
        //每隔1秒调用一次
        new Thread(()-> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(LocalDateTime.now() + " MergeConsumer Receive results ======> " + mergeService.mergeResult());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}