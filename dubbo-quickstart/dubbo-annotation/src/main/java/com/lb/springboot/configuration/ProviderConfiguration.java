package com.lb.springboot.configuration;

import com.lb.springboot.service.GreetingAnnotationService;
import com.lb.springboot.service.impl.GreetingAnnotationServiceImplV3;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfiguration {

    @Bean
    public ServiceConfig greetingAnnotationService(){

        //注意!!! ServiceConfig为重对象，内部封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig<GreetingAnnotationService> serviceConfig = new ServiceConfig<>();

        //服务实现
        GreetingAnnotationService greetingAnnotationService = new GreetingAnnotationServiceImplV3();

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(-1);
        protocol.setThreads(200);

        // 连接注册中心配置
        //通过 Java Config 配置进行关联特定注册中心
        RegistryConfig registry = new RegistryConfig();
        registry.setId("zk-registry");
        registry.setAddress("zookeeper://127.0.0.1:2181");
        //多个注册中心可以用setRegistries()
        serviceConfig.setRegistry(registry);
        serviceConfig.setProtocol(protocol);
        serviceConfig.setInterface(GreetingAnnotationService.class);
        serviceConfig.setRef(greetingAnnotationService);
        serviceConfig.setGroup("qa");
        serviceConfig.setVersion("v3.0");

        // 暴露及注册服务
        serviceConfig.export();

        return serviceConfig;
    }
}
