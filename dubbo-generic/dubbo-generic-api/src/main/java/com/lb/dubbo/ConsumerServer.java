package com.lb.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcContextAttachment;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.UUID;
import java.util.concurrent.locks.LockSupport;

public class ConsumerServer {


    public static void main(String[] args){
        // 引用远程服务
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("standard-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GenericService.class);
        //referenceConfig.setGeneric("true");
        referenceConfig.setTimeout(4000);
        //获取接口
        GenericService genericService = referenceConfig.get();
        //设置隐式参数
        RpcContextAttachment attachment = RpcContext.getClientAttachment();
        attachment.setAttachment("traceId", UUID.randomUUID().toString());
        System.err.println("客户端发起调用...");
        //创建订单
        Object result = genericService.$invoke("sayHello", new String[]{"name"}, new Object[]{"dubbo"});
        System.err.println("客户端收到响应... result=" + result);
        LockSupport.park();
    }
}
