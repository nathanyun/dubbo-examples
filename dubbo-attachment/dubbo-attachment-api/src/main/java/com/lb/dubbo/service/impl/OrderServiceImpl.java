package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.OrderService;
import com.lb.dubbo.service.PaymentService;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcContextAttachment;

public class OrderServiceImpl implements OrderService {

    @Override
    public String create(String productName) {
        //获取隐式参数
        RpcContextAttachment attachment = RpcContext.getServerAttachment();
        String traceId = attachment.getAttachment("traceId");
        System.err.println("订单开始 ... traceId = " + traceId);

        //获取dubbo接口
        ReferenceConfig<PaymentService> reference = new ReferenceConfig<>();
        reference.setInterface(PaymentService.class);
        reference.setProtocol("dubbo");
        reference.setTimeout(5000);
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setGroup("payment");
        PaymentService paymentService = reference.get();

        //远程调用
        String result = paymentService.payment(productName);
        System.err.println("订单结束");
        return result;
    }

}
