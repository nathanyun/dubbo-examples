package com.lb.dubbo;

import com.lb.dubbo.service.GreetingServiceGeneric;
import com.lb.dubbo.zk.EmbeddedZooKeeper;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;

public class ProviderServer {

    public static void main(String[] args) {

        //启动zk客户端
        new EmbeddedZooKeeper(2181, false).start();

        //配置接口服务
        ServiceConfig<GenericService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(GenericService.class);
        serviceConfig.setRef(new GreetingServiceGeneric());

        DubboBootstrap.newInstance()
                .application(new ApplicationConfig("generic-provider"))
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
