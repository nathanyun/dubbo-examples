package com.lb.springboot;


import com.lb.springboot.dto.Order;
import com.lb.springboot.service.GreetingService;
import com.lb.springboot.service.OrderService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DubboBootstrapConsumer {

    public static void main(String[] args) {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("bootstrap-consumer");
        application.setDefault(true);

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        //每隔2秒进行1次远程调用
        while (true){
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception ignored){ }
            //调用远程服务
            referenceByGreeting(registry, application);
            //调用远程服务
            referenceByOrder(registry, application);
        }
    }

    private static void referenceByGreeting(RegistryConfig registry, ApplicationConfig application){
        //注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        //引用远程服务配置
        ReferenceConfig<GreetingService> reference = new ReferenceConfig<>();
        //指定注册中心
        reference.setRegistry(registry);
        //指定应用配置
        reference.setApplication(application);
        //接口
        reference.setInterface(GreetingService.class);
        reference.setVersion("v1.0.0");

        //方法级配置(可选)
        ArrayList<MethodConfig> methodConfigs = new ArrayList<>();
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("sayHi");
        methodConfig.setTimeout(4000);//提供者service耗时2秒, Dubbo发布超时时间设置为3秒, 消费者设置为1秒超时
        methodConfig.setRetries(2);//超时重试
        methodConfigs.add(methodConfig);
        reference.setMethods(methodConfigs);

        //获取远程接口对象
        //注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        GreetingService greetingService = reference.get();
        String result = greetingService.sayHi(reference.getApplication().getName());
        System.out.println("result1 ===> " + result);
    }

    private static void referenceByOrder(RegistryConfig registry, ApplicationConfig application){
        //引用远程服务配置
        ReferenceConfig<OrderService> reference2 = new ReferenceConfig<>();
        //指定注册中心
        reference2.setRegistry(registry);
        //指定应用配置
        reference2.setApplication(application);
        //接口
        reference2.setInterface(OrderService.class);
        reference2.setVersion("v1.0.0");
        reference2.setGroup("qa");//若提供者指定了group,消费者这里必须制定对应的group,否则调用不到服务
        Order result = reference2.get().create(new Order(1L,reference2.getUniqueServiceName(), BigDecimal.ONE, new Date()));
        System.out.println("result2  ===>" + result);
    }
}
