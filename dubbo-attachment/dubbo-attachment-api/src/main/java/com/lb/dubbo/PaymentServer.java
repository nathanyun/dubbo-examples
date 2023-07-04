package com.lb.dubbo;

import com.lb.dubbo.service.PaymentService;
import com.lb.dubbo.service.impl.PaymentServiceImpl;
import com.lb.dubbo.zk.EmbeddedZooKeeper;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class PaymentServer {

    public static void main(String[] args) {
        //配置接口服务
        ServiceConfig<PaymentService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(PaymentService.class);
        serviceConfig.setRef(new PaymentServiceImpl());
        serviceConfig.setGroup("payment");

        DubboBootstrap.newInstance().application(new ApplicationConfig("payment-provider"))
                //注册中心配置
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .protocol(new ProtocolConfig("dubbo", 20882))
                //注册服务
                .service(serviceConfig)
                //启动dubbo服务
                .start()
                .await();
    }
}
