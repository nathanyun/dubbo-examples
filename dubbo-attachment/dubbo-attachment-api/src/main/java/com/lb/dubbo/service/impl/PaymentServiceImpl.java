package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.DeliverService;
import com.lb.dubbo.service.PaymentService;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcContextAttachment;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public String payment(String name) {
        //获取隐式参数
        RpcContextAttachment attachment = RpcContext.getServerAttachment();
        String traceId = attachment.getAttachment("traceId");
        System.out.println("支付开始... traceId = " + traceId);
        //获取dubbo接口
        ReferenceConfig<DeliverService> reference = new ReferenceConfig<>();
        reference.setInterface(DeliverService.class);
        reference.setProtocol("dubbo");
        reference.setTimeout(5000);
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setGroup("deliver");
        DeliverService deliverService = reference.get();
        //远程调用
        String delivered = deliverService.deliver(name);
        System.err.println("支付结束");
        return delivered;
    }


}
