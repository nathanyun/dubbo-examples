package com.lb.dubbo;

import com.lb.dubbo.service.impl.GreetingServiceImpl;
import com.lb.dubbo.service.impl.OrderServiceImpl;
import com.lb.dubbo.service.GreetingService;
import com.lb.dubbo.service.OrderService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import java.io.IOException;

public class DubboBootstrapProvider {

    /**
     * 通过 DubboBootstrap API 可以减少重复配置，更好控制启动过程，支持批量发布/订阅服务接口，还可以更好支持 Dubbo3 的应用级服务发现。
     */
    public static void main(String[] args) throws IOException {
        //注册中心配置
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");

        //服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(-1);
        protocol.setThreads(200);

        //配置中心
        ConfigCenterConfig configCenter = new ConfigCenterConfig();
        configCenter.setGroup("dev");
        configCenter.setCheck(false);
        configCenter.setAddress("zookeeper://127.0.0.1:2181");

        //应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("bootstrap-provider");
        applicationConfig.setDefault(true);

        //第一个提供者
        //注意!!! ServiceConfig为重对象，内部封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig<GreetingService> greeting = new ServiceConfig<>();
        greeting.setInterface(GreetingService.class);
        greeting.setRef(new GreetingServiceImpl());
        greeting.setVersion("v1.0.0");
        greeting.setTimeout(3000);

        //第二个提供者
        ServiceConfig<OrderService> order = new ServiceConfig<>();
        order.setInterface(OrderService.class);
        order.setRef(new OrderServiceImpl());
        order.setVersion("v1.0.0");
        order.setGroup("qa");

        DubboBootstrap.getInstance()
                .application(applicationConfig)//自定义应用名称
                .registry(registryConfig) // 注册中心配置
                .configCenter(configCenter)
                .protocol(protocol)//统一协议
                .service(greeting)
                .service(order)
                .start()//启动Dubbo
                .await();//挂起等待, 防止进程退出
    }

}
