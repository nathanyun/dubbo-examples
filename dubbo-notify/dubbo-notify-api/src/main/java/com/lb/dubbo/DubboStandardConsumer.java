package com.lb.dubbo;


import com.lb.dubbo.service.GreetingService;
import com.lb.dubbo.service.OrderService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.util.concurrent.TimeUnit;

/**
 * Dubbo标准方式消费者API配置
 */
public class DubboStandardConsumer {

    public static void main(String[] args){
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("standard-consumer");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        // 引用远程服务
        ReferenceConfig<GreetingService> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(GreetingService.class);
        reference.setVersion("v2.0");

        //引用远程服务2
        ReferenceConfig<OrderService> reference2 = new ReferenceConfig<>();
        reference2.setApplication(application);
        reference2.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference2.setInterface(OrderService.class);
        reference2.setVersion("v2.0");

        while (true){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ignored) { }
            System.out.println("result1 ===> " + reference.get().sayHi(reference.getUniqueServiceName()));
            System.out.println("result2 ===> " + reference2.get().find());
        }
    }

}
