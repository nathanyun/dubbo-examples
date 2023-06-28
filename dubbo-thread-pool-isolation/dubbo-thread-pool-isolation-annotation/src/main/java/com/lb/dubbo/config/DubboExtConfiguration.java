package com.lb.dubbo.config;

import org.apache.dubbo.config.ProtocolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboExtConfiguration {


    // expose services with dubbo protocol
    @Bean
    public ProtocolConfig dubbo() {
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo");
        protocolConfig.setPort(-1);
        return protocolConfig;
    }

    // expose services with tri protocol
    @Bean
    public ProtocolConfig tri() {
        ProtocolConfig protocolConfig = new ProtocolConfig("tri");
        protocolConfig.setPort(-1);
        return protocolConfig;
    }

    //自定义线程池
    @Bean("greetingServiceExecutor")
    public GreetingServiceExecutor greetingServiceExecutor(){
        return new GreetingServiceExecutor(1,2,60,5);
    }

    //自定义线程池
    @Bean("orderServiceExecutor")
    public OrderServiceExecutor orderServiceExecutor(){
        return new OrderServiceExecutor(1,2,60,5);
    }
}
