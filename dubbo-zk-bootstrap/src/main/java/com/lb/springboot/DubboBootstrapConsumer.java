package com.lb.springboot;


import com.lb.springboot.service.GreetingService;
import org.apache.dubbo.config.AbstractConfig;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.model.ApplicationModel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class DubboBootstrapConsumer {

    public static void main(String[] args) throws InterruptedException {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("bootstrap-consumer");
        application.setDefault(true);

        ApplicationModel applicationModel = ApplicationModel.defaultModel();
        application.setScopeModel(applicationModel);

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        //注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        //引用远程服务配置
        ReferenceConfig<GreetingService> reference = new ReferenceConfig<>();
        //指定注册中心
        reference.setRegistry(registry);
        //指定应用配置
        reference.setApplication(application);
        //接口
        reference.setInterface(GreetingService.class);
        reference.setVersion("v1.0.0");

        //方法级配置(可选)
        ArrayList<MethodConfig> methodConfigs = new ArrayList<>();
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("sayHi");
        methodConfig.setTimeout(4000);//提供者service耗时2秒, Dubbo发布超时时间设置为3秒, 消费者设置为1秒超时
        methodConfig.setRetries(2);//超时重试
        methodConfigs.add(methodConfig);
        reference.setMethods(methodConfigs);

        //获取远程接口对象
        //注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        GreetingService greetingService = reference.get();

        //每隔2秒进行1次远程调用
        while (true){
            try {
                TimeUnit.SECONDS.sleep(2);
                String result = greetingService.sayHi(reference.getApplication().getName());
                System.out.println("result = " + result);
            }catch (Exception ignored){ }
        }

    }

}
