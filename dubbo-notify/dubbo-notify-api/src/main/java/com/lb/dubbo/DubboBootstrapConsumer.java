package com.lb.dubbo;


import com.lb.dubbo.service.GreetingService;
import com.lb.dubbo.service.NotifyService;
import com.lb.dubbo.service.impl.NotifyServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DubboBootstrapConsumer {

    public static void main(String[] args) {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("bootstrap-consumer");
        application.setDefault(true);

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        //引用远程服务配置
        ReferenceConfig<GreetingService> reference = new ReferenceConfig<>();
        reference.setRegistry(registry);
        reference.setApplication(application);
        reference.setInterface(GreetingService.class);
        reference.setVersion("v1.0.0");

        //通知服务
        NotifyService notifyService = new NotifyServiceImpl();

        //方法级配置(可选)
        ArrayList<MethodConfig> methodConfigs = new ArrayList<>();
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("sayHi");
        methodConfig.setTimeout(6000);
        methodConfig.setOninvoke(notifyService);
        methodConfig.setOnreturn(notifyService);
        methodConfig.setOnthrow(notifyService);
        methodConfig.setOninvokeMethod("onInvoke");
        methodConfig.setOnreturnMethod("onReturn");
        methodConfig.setOnthrowMethod("onThrow");
        methodConfigs.add(methodConfig);
        reference.setMethods(methodConfigs);

        //远程调用
        GreetingService greetingService = reference.get();
        String start = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(start + "  准备请求远程调用...");

        //远程调用
        String syncResult = greetingService.sayHi("tonny");
        String end = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(end + "  sayHi result = " + syncResult);

    }
}
