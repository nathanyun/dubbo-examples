package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.util.concurrent.TimeUnit;

/**
 * 一致性哈希负载均衡方式, 调用提供者
 * 说明: 将请求进行一致性hash, 例如R1请求到 S1提供者, R2请求到S2提供者, R3请求到S4提供者...
 */
public class ConsistentHashConsumer1 {

    public static void main(String[] args) throws InterruptedException {

        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setApplication(new ApplicationConfig("consumer-01"));
        //指定一致性哈希负载均衡
        referenceConfig.setLoadbalance("consistenthash");

        //获取远程接口
        GreetingService greetingService = referenceConfig.get();

        while (true){
            TimeUnit.SECONDS.sleep(2);
            greetingService.sayHi("jack");
        }
    }
}
