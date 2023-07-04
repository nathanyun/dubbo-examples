package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import com.lb.dubbo.service.impl.GreetingServiceImpl;
import com.lb.dubbo.zk.EmbeddedZooKeeper;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class Provider1 {

    public static void main(String[] args) {

        //启动嵌入式zk(如果你本地已经启动zk, 那就注释掉这行代码)
        new EmbeddedZooKeeper(2181, false).start();

        //发布dubbo服务
        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setTimeout(5000);
        serviceConfig.setRef(new GreetingServiceImpl());

        //启动dubbo应用
        DubboBootstrap.newInstance().application(new ApplicationConfig("provider-01"))
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .protocol(new ProtocolConfig("dubbo", 20881))
                .service(serviceConfig)
                .start()
                .await();
    }
}
