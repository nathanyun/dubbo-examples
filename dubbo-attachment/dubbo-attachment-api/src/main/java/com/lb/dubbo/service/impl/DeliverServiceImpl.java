package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.DeliverService;
import org.apache.dubbo.rpc.RpcContext;

public class DeliverServiceImpl implements DeliverService {

    @Override
    public String deliver(String name) {
        //获取隐式参数
        String traceId = RpcContext.getServerAttachment().getAttachment("traceId");
        System.err.println("物流中... traceId = " + traceId);
        return String.format("您的商品 productName: %s, 物流单号是: %s", name, traceId);
    }

}
