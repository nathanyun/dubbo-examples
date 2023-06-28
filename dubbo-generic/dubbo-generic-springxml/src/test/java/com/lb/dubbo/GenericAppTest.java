package com.lb.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiConsumer;

public class GenericAppTest {

    private static GenericService genericService;

    /**
     * 服务端定义异步接口, 消费端异步调用
     */
    public static void main(String[] args) {
        //应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig("generic-call-consumer");
        applicationConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        //接口配置
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface("com.lb.dubbo.service.GreetingService");
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setGeneric("true");
        referenceConfig.setAsync(true);
        referenceConfig.setTimeout(4000);

        //获取远程调用接口
        genericService = referenceConfig.get();

        CompletableFuture<Object> future = genericService.$invokeAsync("sayHi", new String[]{"java.lang.String"}, new Object[]{"world"});
        // 增加回调
        future.whenComplete(new BiConsumer<Object, Throwable>() {
            @Override
            public void accept(Object result, Throwable throwable) {
                if (throwable == null) {
                    System.out.println(LocalDateTime.now() + " - Response: " + result);
                } else {
                    throwable.printStackTrace();
                }
            }
        });
        // 早于结果输出
        System.out.println(LocalDateTime.now() + " - Executed before response return.");
        //阻塞等待5秒,以便获取响应结果
        LockSupport.parkNanos(5000000000L);
    }

}
