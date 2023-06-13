package com.lb.springboot;

import com.lb.springboot.service.GreetingService;
import com.lb.springboot.service.OrderService;
import com.lb.springboot.service.impl.GreetingServiceImpl;
import com.lb.springboot.service.impl.OrderServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.MetadataReportConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

/**
 * Dubbo标准API提供者配置
 */
public class DubboStandardProvider {
    public static void main(String[] args) throws IOException {

        //配置中心
        ConfigCenterConfig configCenter = new ConfigCenterConfig();
        configCenter.setAddress("zookeeper://127.0.0.1:2181");

        //注册中心
        RegistryConfig registry = new RegistryConfig("zookeeper://127.0.0.1:2181");

        //元数据
        MetadataReportConfig metadataReport = new MetadataReportConfig();
        metadataReport.setAddress("zookeeper://127.0.0.1:2181");

        //应用配置
        ApplicationConfig application = new ApplicationConfig("standard-provider");
        application.setRegistry(registry);
        application.setDefault(true);

        //协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(12345);
        protocol.setThreads(200);

        //提供者服务
        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(application);
        serviceConfig.setConfigCenter(configCenter);
        serviceConfig.setMetadataReportConfig(metadataReport);
        serviceConfig.setRegistry(registry);
        serviceConfig.setProtocol(protocol);
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingServiceImpl());
        serviceConfig.setVersion("v2.0");
        serviceConfig.setTimeout(4000);

        //暴露并注册Dubbo服务
        serviceConfig.export();

        //提供者服务
        ServiceConfig<OrderService> serviceConfig2 = new ServiceConfig<>();
        serviceConfig2.setApplication(application);
        serviceConfig2.setMetadataReportConfig(metadataReport);
        serviceConfig2.setRegistry(registry);
        serviceConfig2.setProtocol(protocol);
        serviceConfig2.setInterface(OrderService.class);
        serviceConfig2.setRef(new OrderServiceImpl());
        serviceConfig2.setVersion("v2.0");
        //暴露并注册Dubbo服务
        serviceConfig2.export();

        //阻塞主线程(防止进程退出, 需要手动释放)
        LockSupport.park();
    }

}
