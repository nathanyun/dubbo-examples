package com.lb.dubbo;

import com.lb.dubbo.config.GreetingServiceExecutor;
import com.lb.dubbo.config.OrderServiceExecutor;
import com.lb.dubbo.service.GreetingService;
import com.lb.dubbo.service.OrderService;
import com.lb.dubbo.service.impl.GreetingServiceImpl;
import com.lb.dubbo.service.impl.OrderServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class IsolationProvider {

    public static void main(String[] args) {
        //应用配置
        ApplicationConfig application = new ApplicationConfig("isolation-provider-app");
        //设置线程池隔离,默认为default, 不隔离
        application.setExecutorManagementMode("isolation");

        //配置接口服务1
        ServiceConfig<OrderService> orderService = new ServiceConfig<>();
        orderService.setInterface(OrderService.class);
        orderService.setRef(new OrderServiceImpl());
        //指定线程池
        orderService.setExecutor(new OrderServiceExecutor(2, 5, 30, 10));

        //配置接口服务2
        ServiceConfig<GreetingService> greetingService = new ServiceConfig<>();
        greetingService.setInterface(GreetingService.class);
        greetingService.setRef(new GreetingServiceImpl());
        //指定线程池
        greetingService.setExecutor(new GreetingServiceExecutor(2, 5, 30, 10));

        DubboBootstrap.newInstance().application(application)
                //注册中心配置
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                //对外暴露2个协议
                .protocol(new ProtocolConfig("dubbo", 20889))
                .protocol(new ProtocolConfig("tri", 20888))
                //注册服务
                .service(orderService)
                .service(greetingService)
                //启动dubbo服务
                .start()
                .await();
    }
}
