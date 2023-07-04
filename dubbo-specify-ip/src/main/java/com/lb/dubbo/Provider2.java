package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import com.lb.dubbo.service.impl.GreetingServiceImpl;
import com.lb.dubbo.zk.EmbeddedZooKeeper;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class Provider2 {

    public static void main(String[] args) {
        //发布dubbo服务
        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setTimeout(5000);
        serviceConfig.setRef(new GreetingServiceImpl());

        //启动dubbo应用
        DubboBootstrap.newInstance().application(new ApplicationConfig("provider-02"))
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .protocol(new ProtocolConfig("dubbo", 20882))
                .service(serviceConfig)
                .start()
                .await();
    }
}
