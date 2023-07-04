package com.lb.dubbo;

import com.lb.dubbo.service.DeliverService;
import com.lb.dubbo.service.impl.DeliverServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class DeliverServer {

    /**
     * 发布dubbo服务(物流服务)
     * @param args
     */
    public static void main(String[] args) {
        //配置接口服务
        ServiceConfig<DeliverService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(DeliverService.class);
        serviceConfig.setRef(new DeliverServiceImpl());
        serviceConfig.setGroup("deliver");

        DubboBootstrap.newInstance().application(new ApplicationConfig("deliver-provider"))
                //注册中心配置
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .protocol(new ProtocolConfig("dubbo", 20883))
                //注册服务
                .service(serviceConfig)
                //启动dubbo服务
                .start()
                .await();
    }
}
