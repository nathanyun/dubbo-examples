package com.lb.dubbo;

import com.lb.dubbo.config.ServiceExecutorWrapper;
import com.lb.dubbo.service.OrderService;
import com.lb.dubbo.service.impl.OrderServiceImpl;
import com.lb.dubbo.zk.EmbeddedZooKeeper;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class OrderServer {

    public static void main(String[] args) {

        //启动zk客户端
        new EmbeddedZooKeeper(2181, false).start();

        //应用配置
        ApplicationConfig application = new ApplicationConfig("order-provider");
        //设置线程池隔离,默认为default, 不隔离
        application.setExecutorManagementMode("isolation");

        //配置接口服务
        ServiceConfig<OrderService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(OrderService.class);
        serviceConfig.setRef(new OrderServiceImpl());
        serviceConfig.setGroup("order");
        //指定线程池
        serviceConfig.setExecutor(new ServiceExecutorWrapper(2, 5, 30, 10));

        DubboBootstrap.newInstance().application(application)
                //注册中心配置
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .protocol(new ProtocolConfig("dubbo", 20881))
                //注册服务
                .service(serviceConfig)
                //启动dubbo服务
                .start()
                .await();
    }
}
