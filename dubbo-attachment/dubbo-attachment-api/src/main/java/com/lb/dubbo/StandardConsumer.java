package com.lb.dubbo;

import com.lb.dubbo.service.OrderService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcContextAttachment;

import java.util.UUID;
import java.util.concurrent.locks.LockSupport;

public class StandardConsumer {


    public static void main(String[] args){
        // 引用远程服务
        ReferenceConfig<OrderService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("standard-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setInterface(OrderService.class);
        reference.setGroup("order");
        //获取接口
        OrderService orderService = reference.get();
        //设置隐式参数
        RpcContextAttachment attachment = RpcContext.getClientAttachment();
        attachment.setAttachment("traceId", UUID.randomUUID().toString());
        System.err.println("客户端发起调用...");
        //创建订单
        String result = orderService.create("HUAWEI P60 ART");
        System.err.println("客户端收到响应... result="+result);
        LockSupport.park();
    }
}
