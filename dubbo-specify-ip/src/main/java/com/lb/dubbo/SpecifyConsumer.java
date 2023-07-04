package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.cluster.specifyaddress.Address;
import org.apache.dubbo.rpc.cluster.specifyaddress.UserSpecifiedAddressUtil;

/**
 * 指定IP端口调用提供者
 */
public class SpecifyConsumer {

    public static void main(String[] args) {

        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setApplication(new ApplicationConfig("consumer"));

        //获取远程接口
        GreetingService greetingService = referenceConfig.get();

        //指定IP
        UserSpecifiedAddressUtil.setAddress(new Address("127.0.0.1", 20881, true));
        greetingService.sayHi("jack");

        //指定IP
        UserSpecifiedAddressUtil.setAddress(new Address("127.0.0.1", 20882, true));
        greetingService.sayHi("tony");

    }
}
